package com.gala.rabbitmq.receiver;

import com.alibaba.fastjson.JSON;
import com.gala.rabbitmq.entity.DirectMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author YJ
 * @date 2021/9/1
 * @description FanoutReceiver
 */
@Slf4j
@Component
@RabbitListener(queues = "fanout.b")
public class FanoutReceiver {

    @RabbitHandler
    public void process(String data){
        try {
            DirectMessage message = JSON.parseObject(data, DirectMessage.class);
            log.info("fanoutExchange消费者2接收到消息======>" + message);
        } catch (Exception e) {
            log.error("似乎出错了===>", e);
        }
    }
}
