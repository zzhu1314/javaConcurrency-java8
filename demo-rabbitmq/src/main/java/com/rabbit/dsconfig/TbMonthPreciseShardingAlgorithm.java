package com.rabbit.dsconfig;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;


/**
 * 数据库表分片算法
 * @author LiaoYao
 * @date：2018年8月30日
 */
public class TbMonthPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Date> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TbMonthPreciseShardingAlgorithm.class);
	
	@Override
	public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
//		LOGGER.info("tables:"+availableTargetNames.toString() + "\n columnName:" + shardingValue.getColumnName() + "value:" + shardingValue.getValue());
		if (shardingValue.getColumnName().compareToIgnoreCase("swipe_time") == 0) {//shardingValue 有三个值 逻辑表名  字段  value
			Date value = shardingValue.getValue();
			Object[] arrTagetName = availableTargetNames.toArray();//得到表名列表
			int index = 0;
			try {
				//按照季度分表
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(value);
				index = calendar.get(Calendar.MONTH);
//				index = Integer.parseInt(value) - 1;
			} catch (Exception e) {
				LOGGER.error("error param passenger_month!");
				return null;//若传入的value有问题
			}
			String result =  (String) arrTagetName[index];//按照索引找到这个表名
			LOGGER.info("pre table:"+result);
			return result;//返回的是表名
		}
		System.err.println("col name :"+shardingValue.getColumnName()+"is not find");;
		return null;
	}


}
