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
 * @description DirectReceiver
 */
@Slf4j
@Component
@RabbitListener(queues = "TestDirectQueue")
public class DirectReceiver {

    @RabbitHandler
    public void process(String msg) {
        try {
            DirectMessage message = JSON.parseObject(msg, DirectMessage.class);
            log.info("DirectReceiver消费者2接收到消息：" + message);
        } catch (Exception e) {
            log.error("似乎出错了===>", e);
        }
    }
}
