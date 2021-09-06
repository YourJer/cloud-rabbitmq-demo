package com.gala.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YJ
 * @date 2021/9/1
 * @description RabbitConfig
 */
@Slf4j
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory，才能触发回调函数，无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息推送到交换机成功！"+cause);
            } else {
                log.info("消息推送到交换机失败！===>"+cause);
            } 
        });

        rabbitTemplate.setReturnsCallback(returned -> {
            log.info("消息内容===>"+returned.getMessage());
            log.info("replyCode===>"+returned.getReplyCode());
            log.info("replyText===>"+returned.getReplyText());
            log.info("exchange===>"+returned.getExchange());
            log.info("routingKey===>"+returned.getRoutingKey());
        });
        return rabbitTemplate;
    }
}
