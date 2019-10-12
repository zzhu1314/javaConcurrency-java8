package com.rabbit.config;


import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import java.time.Duration;

@Configuration
public class

RedisConfig extends CachingConfigurerSupport {
    @Value("${spring.redis.host}")
    private String hostName;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.lettuce.pool.max-active}")
    private int maxTotal;
    @Value("${spring.redis.lettuce.pool.max-wait}")
    private String maxWaitMillis;
    @Value("${spring.redis.lettuce.pool.min-idle}")
    private int minIdle;
    @Value("${spring_redis.public_key}")
    private String publicKey;
    @Value("${spring.redis.timeout}")
    private String conTimeOut;
    @Value("${spring.redis.sentinel.master:#{null}}")
    private String master;
    @Value("${spring.redis.sentinel.nodes:#{null}}")
    private String nodes;
    @Value("${spring.redis.database}")
    private int dataBase;


    private RedisNode getRedisNode(String node) {
        String slave[] = node.split(":");
        RedisNode redisNode = new RedisNode(slave[0], Integer.valueOf(slave[1]));
        return redisNode;
    }
    /**
     * redis连接池配置
     * @return
     */
    @Bean
     public GenericObjectPoolConfig genericObjectPoolConfig(){
         GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
         genericObjectPoolConfig.setMaxTotal(maxTotal);
         genericObjectPoolConfig.setMinIdle(minIdle);
         genericObjectPoolConfig.setMaxIdle(maxIdle);
         genericObjectPoolConfig.setMaxWaitMillis(longms(maxWaitMillis));
         return genericObjectPoolConfig;
     }

    /**
     * redis配置
     * @param genericObjectPoolConfig
     * @return
     * @throws Exception
     */
    @Bean
   public RedisConnectionFactory redisConnectionFactory(GenericObjectPoolConfig genericObjectPoolConfig) throws Exception {
         LettuceConnectionFactory factory;

         RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
         redisStandaloneConfiguration.setDatabase(dataBase);
         redisStandaloneConfiguration.setHostName(hostName);
         redisStandaloneConfiguration.setPort(port);
         redisStandaloneConfiguration.setPassword(RSAToolsConfig.decrypt(publicKey, password));

        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder  builder =LettucePoolingClientConfiguration.builder();
        builder.poolConfig(genericObjectPoolConfig);
        builder.commandTimeout(Duration.ofMillis(longms(conTimeOut)));

         if(StringUtils.isEmpty(master)){
             factory= new LettuceConnectionFactory(redisStandaloneConfiguration,
                     builder.build());
         }else{
             RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration().master(master);
             for (String node : nodes.split(",")) {
                 sentinelConfig.addSentinel(getRedisNode(node));
             }
             factory = new LettuceConnectionFactory(sentinelConfig,builder.build());
             factory.setDatabase(dataBase);
             factory.setHostName(hostName);
             factory.setPort(port);
             factory.setPassword(RSAToolsConfig.decrypt(publicKey, password));
         }
        return factory;


   }

    @Bean("platform-redis")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
         RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());//key序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());//value序列化
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }



    private  Long longms(String value){
        Long result = Long.valueOf(value.substring(0,value.length()-2));
        return result;
    }


}
