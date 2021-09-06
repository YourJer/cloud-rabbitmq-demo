package com.gala.rabbitmq.receiver;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.gala.rabbitmq.entity.DirectMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @author YJ
 * @date 2021/9/2
 * @description MyAckReceiver
 */
@Slf4j
@Component
public class MyAckReceiver implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            String msg = message.toString();
            String[] split = msg.split("'");
            DirectMessage directMessage = JSON.parseObject(split[1].trim(), DirectMessage.class);
            log.info("接收到消息======>" + directMessage);
            log.info("消息来自======>" + message.getMessageProperties().getConsumerQueue());
            channel.basicAck(deliveryTag, false);
//            channel.basicReject(deliveryTag, true);
        } catch (Exception e){
            channel.basicReject(deliveryTag, false);
            log.info("出错了======>", e);
        }
    }
}
