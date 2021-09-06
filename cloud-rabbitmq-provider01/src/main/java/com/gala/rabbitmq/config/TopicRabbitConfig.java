package com.gala.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YJ
 * @date 2021/8/31
 * @description TopicRabbitConfig
 */
@Configuration
public class TopicRabbitConfig {
    //绑定键
    public final static String TIGER = "topic.tiger";
    public final static String LION = "topic.lion";

    @Bean
    public Queue firstQueue(){
        return new Queue(TIGER);
    }
    @Bean
    public Queue secondQueue(){
        return new Queue(LION);
    }
//    @Bean
//    public Queue thirdQueue(){
//        return new Queue(LION);
//    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 将firstQueue和topicExchange绑定，而且绑定的键值为topic.tiger
     * 这样只要是消息携带的路由键是topic.tiger，才会分发到该队列
     */
    @Bean
    public Binding bindingExchangeMessage(){
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(TIGER);
    }

    /**
     * 将secondQueue和topicExchange绑定，而且绑定的键值为topic.#
     * 这样只要是消息携带的路由键是topic.开头，都会分发到该队列
     */
//    @Bean
//    public Binding bindingExchangeMessage2(){
//        return BindingBuilder.bind(secondQueue()).to(exchange()).with(LION);
//    }

    /**
     * 将secondQueue和topicExchange绑定，而且绑定的键值为topic.#
     * 这样只要是消息携带的路由键是topic.开头，都会分发到该队列
     */
    @Bean
    public Binding bindingExchangeMessage2(){
        return BindingBuilder.bind(secondQueue()).to(exchange()).with("topic.#");
    }
}
