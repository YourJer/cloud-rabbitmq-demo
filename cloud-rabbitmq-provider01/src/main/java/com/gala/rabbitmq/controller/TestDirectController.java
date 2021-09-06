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
 * @date 2021/8/30
 * @description TestDirectController
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestDirectController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/directQueue1")
    public String testDirect1(@RequestParam("text") String text) {
        String msg = CommonUtil.getMsgMap(text);
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", msg);
        log.info("消息推送成功===>" + msg);
        return "ok";
    }

    @PostMapping("/directQueue2")
    public String testDirect2(@RequestParam("text") String text) {
        String msg = CommonUtil.getMsgMap(text);
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", msg);
        log.info("消息推送成功===>" + msg);
        return "ok";
    }
}
