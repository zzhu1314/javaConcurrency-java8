package com.rabbit.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/24  15:00
 **/
@Component
@RabbitListener(queues = "queue.name")
public class MqDemo {
    private static Logger LOGGER = LoggerFactory.getLogger(MqDemo.class);

    @RabbitHandler
    public void process(String message){
        System.out.println("广播模式接收到的消息为"+message);
        int i = 1/0;
        LOGGER.info("广播模式接收到的消息为:{}",message);
     }
}
