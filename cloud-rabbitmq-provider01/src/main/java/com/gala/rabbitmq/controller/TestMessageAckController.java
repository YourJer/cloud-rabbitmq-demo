package com.gala.rabbitmq.controller;

import com.gala.rabbitmq.constants.Constants;
import com.gala.rabbitmq.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YJ
 * @date 2021/9/2
 * @description TestMessageAckController
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestMessageAckController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/messageAck")
    public String testMessageAck(String text) {
        String msgMap = CommonUtil.getMsgMap(text);
        rabbitTemplate.convertAndSend("no-exist-exchange", "TestDirectRouting", msgMap);
        return Constants.OK;
    }

    @PostMapping("/noQueue")
    public String testMessageNoQueue(String text) {
        String msgMap = CommonUtil.getMsgMap(text);
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", msgMap);
        return Constants.OK;
    }
}
