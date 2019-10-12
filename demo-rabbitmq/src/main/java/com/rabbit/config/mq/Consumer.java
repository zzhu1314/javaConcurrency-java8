package com.rabbit.config.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author:zhuzhou
 * @Date: 2019/10/10  10:55
 **/
@Component
@Slf4j
public class Consumer implements ChannelAwareMessageListener {


    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            MessageProperties messageProperties = message.getMessageProperties();

            long deliveryTag = messageProperties.getDeliveryTag();//由生产者产生的tagId
            /// 如果是重复投递的消息，redelivered 为 true
            Boolean redelivered = messageProperties.getRedelivered();
            //获取生产者发送的信息
            byte[] body = message.getBody();
            String fromMessage = new String(body);
            log.info("consume Message = {},deliveryTag = {},redelivered = {}",fromMessage,deliveryTag,redelivered);

            // 代表消费者确认收到当前消息，第二个参数表示一次是否 ack 多条消息
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            //当出现异常时，我们需要把这个消息回滚到消息队列，有两种方式
            //ack返回false，并重新回到队列，api里面解释得很清楚
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            //拒绝消息
            //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }

    }
}
