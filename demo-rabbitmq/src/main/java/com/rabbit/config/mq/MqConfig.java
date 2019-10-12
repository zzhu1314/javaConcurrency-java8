package com.rabbit.config.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author:zhuzhou
 * @Date: 2019/9/25  9:43
 **/
@Configuration
@Slf4j
public class MqConfig {

    @Value("${rabbitmq.fanout.exchange_name}")
    private  String  fanout_exchange_name;
    @Value("${rabbitmq.fanout.queue_name}")
    private  String  fanout_queue_name;
    @Bean
    public FanoutExchange fanoutExchange(){
        log.info("交换机实例创建成功:{}",fanout_exchange_name);
        return new FanoutExchange(fanout_exchange_name);
    }

    @Bean
    public Queue queue(){
        return new Queue(fanout_queue_name);
    }
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }

    @Bean
    public  ConnectionFactory connectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost("175.168.1.136");
        cachingConnectionFactory.setPort(5672);
        cachingConnectionFactory.setUsername("root");
        cachingConnectionFactory.setPassword("123456");
        cachingConnectionFactory.setPublisherReturns(true);
        cachingConnectionFactory.setPublisherConfirms(true);
        return cachingConnectionFactory;
    }
    //监听消费者消费队列，手动确认
    @Bean
    public SimpleMessageListenerContainer   container(ConnectionFactory connectionFactory, ChannelAwareMessageListener channelAwareMessageListener){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(channelAwareMessageListener);
        container.setQueueNames("queue.name");
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setPrefetchCount(300);
        return container;
    }
    //消息发送前可把消息存储到redis或者存储到本地（对发送失败的消息进行处理）
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);//判断消息是否根据路由发送到了对应的队列，若没有，返回给生产者
        //判断是否发送到exchange上
        rabbitTemplate.setConfirmCallback((correlationData, ack, s) -> {
            if (ack){
                log.info("{} sending success",correlationData.getId());
              }else{
                log.info("发送至 {} 失败",correlationData.getReturnedMessage().getMessageProperties().getReceivedExchange());
                //TODO 将消息做处理重新发送
            }

        });
        //判断消息未发送到exchange绑定的队列上，触发事件
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("ReturnCallback unroutable messages, message = {} , replyCode = {} , replyText = {} , exchange = {} , routingKey = {} ", message, replyCode, replyText, exchange, routingKey);
            //TODO 将消息做处理重新发送
        });
        return rabbitTemplate;
    }

}
