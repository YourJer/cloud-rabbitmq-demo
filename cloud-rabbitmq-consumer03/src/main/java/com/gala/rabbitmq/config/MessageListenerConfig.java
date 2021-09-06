package com.gala.rabbitmq.config;

import com.gala.rabbitmq.receiver.MyAckReceiver;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YJ
 * @date 2021/9/2
 * @description MessageListenerConfig
 */
@Configuration
public class MessageListenerConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private MyAckReceiver myAckReceiver;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        //默认是自动确认，这里改成手动确认消息
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setQueueNames("TestDirectQueue");

        /*另一种设置队列的方法，如果使用这种情况，那么要设置多个，就要用addQueues
        container.setQueues(new Queue("queue1", true));
        container.addQueues(new Queue("queue2", true));*/
        container.setMessageListener(myAckReceiver);
        return container;
    }
}
