package com.gala.rabbitmq.controller;

import com.gala.rabbitmq.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YJ
 * @date 2021/8/31
 * @description TestTopicController
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestTopicController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/topicMessage1")
    public String topicMessage1(@RequestParam("text") String text) {
        String msg = CommonUtil.getMsgMap(text);
        rabbitTemplate.convertAndSend("topicExchange", "topic.tiger", msg);
        log.info("消息推送成功===>" + msg);
        return "ok";
    }

    @PostMapping("/topicMessage2")
    public String topicMessage2(@RequestParam("text") String text) {
        String msg = CommonUtil.getMsgMap(text);
        rabbitTemplate.convertAndSend("topicExchange", "topic.lion", msg);
        log.info("消息推送成功===>" + msg);
        return "ok";
    }
}
