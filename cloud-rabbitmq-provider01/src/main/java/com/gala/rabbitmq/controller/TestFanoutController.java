package com.gala.rabbitmq.controller;

import com.gala.rabbitmq.constants.Constants;
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
 * @date 2021/9/1
 * @description TestFanoutController
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestFanoutController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/fanoutExchange")
    public String fanoutExchange(@RequestParam("text") String text) {
        String msgMap = CommonUtil.getMsgMap(text);
        rabbitTemplate.convertAndSend("fanoutExchange", "", msgMap);
        log.info("消息推送成功===>" + msgMap);
        return Constants.OK;
    }
}
