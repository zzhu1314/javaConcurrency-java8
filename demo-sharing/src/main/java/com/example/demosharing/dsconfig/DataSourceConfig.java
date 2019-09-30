
package com.example.demosharing.dsconfig;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.core.constant.properties.ShardingPropertiesConstant;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Configuration
public class DataSourceConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);
	private String subTbByMonthNames = "face_swipe_record";
	
	@Autowired
    private Environment env;//获取自定义配置
	
	@Value("${face.database.name}")
	private String faceDBName;
	
	//通用数据库配置
	@Value("${spring.datasource.druid.initial-size}")
	private String initialSize;

	@Value("${spring.datasource.druid.max-active}")
	private String maxActive;

	@Value("${spring.datasource.druid.min-idle}")
	private String minIdle;

	@Value("${spring.datasource.druid.max-wait}")
	private String maxWait;

	@Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
	private String timeBetweenEvictionRunsMillis;

	@Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
	private String minEvicatableIdleTimeMillis;

	@Value("${spring.datasource.druid.validation-query}")
	private String validationQuery;

	@Value("${spring.datasource.druid.test-while-idle}")
	private String testWhileIdle;
	
	@Value("${spring.datasource.druid.test-on-borrow}")
	private String testOnBorrow;
	
	@Value("${spring.datasource.druid.test-on-return}")
	private String testOnReturn;
	
	@Value("${public-key}")
	private String publicKey;
	
	@Value("${spring.datasource.druid.pool-prepared-statements}")
	private String poolPreparedStatements;

	@Value("${spring.datasource.druid.max-pool-prepared-statement-per-connection-size}")
	private String maxPoolPreparedStatementPerConnectionSize;

	@Value("${spring.datasource.druid.filters}")
	private String filters;

	@Value("${spring.datasource.druid.connection-properties}")
	private String connectionProperties;
	
	@Value("${sharding-thread.num}")
	private String shardingThreadNum;
	
	@Primary
	@Bean(name = "shardingDataSource", destroyMethod = "close")
	@Qualifier("shardingDataSource")
	public DataSource buildDataSource() throws Exception{
		LOGGER.trace("build shardingDataSource");
		Map<String, DataSource> dsMap =  new HashMap<>();
		dsMap.put(faceDBName, buildBaseDataSource());
		
		List<TableRuleConfiguration> tbRuleConfigs = createTbRuleConfigs();
		
		// 配置分片规则
		ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
		shardingRuleConfiguration.getTableRuleConfigs().addAll(tbRuleConfigs);
		//设置不需要分库的数据源   即 默认的
//		shardingRuleConfiguration.setDefaultDatabaseShardingStrategyConfig(null);
//		shardingRuleConfiguration.setDefaultDataSourceName(baseDBName);
		
		try {
			Properties properties = new Properties();
			LOGGER.info("-----------分片线程数-----------:"+shardingThreadNum);
			properties.put(ShardingPropertiesConstant.EXECUTOR_SIZE.getKey(), shardingThreadNum);
			properties.put(ShardingPropertiesConstant.SQL_SHOW.getKey(), false);
			return ShardingDataSourceFactory.createDataSource(dsMap, shardingRuleConfiguration,
					new ConcurrentHashMap<String, Object>(), properties);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private ArrayList<TableRuleConfiguration> createTbRuleConfigs(){
		ArrayList<TableRuleConfiguration> tbRuleConfigs = new ArrayList<>();
		//首先是按照月份分表	//自定义的按照月份分表的分片算法实现  分表  第一个参数  根据哪一个字段 做分片  第二个参数 根据哪一种分片策略
		StandardShardingStrategyConfiguration standardTbByMonthStrategyConfig = new StandardShardingStrategyConfiguration("swipe_time", 
                new TbMonthPreciseShardingAlgorithm());
		for (String tbName : subTbByMonthNames.split(",")) {
			TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration();
			//设置逻辑表
			tableRuleConfiguration.setLogicTable(tbName);
			//按照季度  分四个表
			String nodeName = String.format("%s.%s_${%d..%d}", faceDBName, tbName, 1, 12);
			tableRuleConfiguration.setActualDataNodes(nodeName);
			tableRuleConfiguration.setTableShardingStrategyConfig(standardTbByMonthStrategyConfig);
			tbRuleConfigs.add(tableRuleConfiguration);
		}
		return tbRuleConfigs;
	}
	
	//创建分表的数据源
	private DataSource buildBaseDataSource() throws Exception{
		 //RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "spring.guard.datasource.");
		Properties properties = setCustomProperties();
		properties.put(DruidDataSourceFactory.PROP_URL, env.getProperty("spring.guard.datasource.url"));//数据库url
		properties.put(DruidDataSourceFactory.PROP_USERNAME, env.getProperty("spring.guard.datasource.username"));//用户名
		// properties.put(DruidDataSourceFactory.PROP_PASSWORD,
		// ConfigTools.decrypt(publicKey,mysqlUserPwd));
		properties.put(DruidDataSourceFactory.PROP_PASSWORD, env.getProperty("spring.guard.datasource.password"));//密码
		properties.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, env.getProperty("spring.guard.datasource.driverClassName"));//Driver  数据库驱动
		LOGGER.info("url:{}",properties.getProperty(DruidDataSourceFactory.PROP_URL));
		return DruidDataSourceFactory.createDataSource(properties);
	}
	
	private Properties setCustomProperties(){
		Properties properties = new Properties();
		properties.put(DruidDataSourceFactory.PROP_INITIALSIZE, initialSize);//初始化时建立物理连接的个数 默认是0  配置10
		properties.put(DruidDataSourceFactory.PROP_MAXACTIVE, maxActive);//最大连接池数量默认是8  50
		properties.put(DruidDataSourceFactory.PROP_MINIDLE, minIdle);//最小连接池数量 10
		properties.put(DruidDataSourceFactory.PROP_MAXWAIT, maxWait);//获取连接时最大等待时间  如果配置了这个值之后 默认使用公平锁 并发效率会有所下降  可以通过配置userUnfairLock 为true使用非公平锁
		properties.put(DruidDataSourceFactory.PROP_TIMEBETWEENEVICTIONRUNSMILLIS, timeBetweenEvictionRunsMillis);//有两个含义： 1) Destroy线程会检测连接的间隔时间2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
		properties.put(DruidDataSourceFactory.PROP_MINEVICTABLEIDLETIMEMILLIS, minEvicatableIdleTimeMillis);
		properties.put(DruidDataSourceFactory.PROP_VALIDATIONQUERY, validationQuery);//用来检测连接是否有效的sql，要求是一个查询语句  如果此处为null 后面三个不会起作用
		properties.put(DruidDataSourceFactory.PROP_TESTWHILEIDLE, testWhileIdle);
		properties.put(DruidDataSourceFactory.PROP_TESTONBORROW, testOnBorrow);
		properties.put(DruidDataSourceFactory.PROP_TESTONRETURN, testOnReturn);
		properties.put(DruidDataSourceFactory.PROP_POOLPREPAREDSTATEMENTS, poolPreparedStatements);//是否缓存是否缓存preparedStatement
		properties.put(DruidDataSourceFactory.PROP_MAXOPENPREPAREDSTATEMENTS,
				maxPoolPreparedStatementPerConnectionSize);
		properties.put(DruidDataSourceFactory.PROP_FILTERS, filters);
		properties.put(DruidDataSourceFactory.PROP_CONNECTIONPROPERTIES, connectionProperties);//物理连接初始化的时候执行的sql
		return properties;
	}
	
}

