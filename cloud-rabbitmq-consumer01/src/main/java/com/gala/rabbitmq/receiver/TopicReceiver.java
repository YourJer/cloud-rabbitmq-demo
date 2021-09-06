package com.gala.rabbitmq.receiver;

import com.alibaba.fastjson.JSON;
import com.gala.rabbitmq.entity.DirectMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author YJ
 * @date 2021/8/31
 * @description TopicReceiver
 */
@Slf4j
@Component
@RabbitListener(queues = "topic.tiger")
public class TopicReceiver {

    @RabbitHandler
    public void process(String data){
        try {
            DirectMessage message = JSON.parseObject(data, DirectMessage.class);
            log.info("TopicReceiver消费者1接收到消息===>" + message);
        } catch (Exception e) {
            log.error("似乎出错了===>", e);
        }
    }
}
