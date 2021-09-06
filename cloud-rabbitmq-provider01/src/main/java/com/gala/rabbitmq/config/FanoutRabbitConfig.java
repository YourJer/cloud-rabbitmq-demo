package com.gala.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YJ
 * @date 2021/9/1
 * @description FanoutRabbitConfig
 */
@Configuration
public class FanoutRabbitConfig {

    public static final String queueA = "fanout.a";
    public static final String queueB = "fanout.b";
    public static final String queueC = "fanout.c";

    @Bean
    public Queue queueA() {
        return new Queue(queueA);
    }
    @Bean
    public Queue queueB() {
        return new Queue(queueB);
    }
    @Bean
    public Queue queueC() {
        return new Queue(queueC);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Binding bindingExchangeA(){
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }
    @Bean
    public Binding bindingExchangeB(){
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }
    @Bean
    public Binding bindingExchangeC(){
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }
}
