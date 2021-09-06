package com.gala.rabbitmq.receiver;

import com.alibaba.fastjson.JSON;
import com.gala.rabbitmq.entity.DirectMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;

/**
 * @author YJ
 * @date 2021/9/1
 * @description FanoutReceiver
 */
@Slf4j
public class FanoutReceiver {
    @RabbitHandler
    public void process(String data){
        try {
            DirectMessage message = JSON.parseObject(data, DirectMessage.class);
            log.info("fanoutExchange消费者3接收到消息======>" + message);
        } catch (Exception e) {
            log.error("似乎出错了===>", e);
        }
    }
}
