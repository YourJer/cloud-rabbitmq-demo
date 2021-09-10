package com.gala.nacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author YJ
 * @date 2021/9/10
 * @description TestConsumerController
 */
@Slf4j
@RestController
@RequestMapping("/nacos")
public class TestConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/{str}")
    public String getRes(@PathVariable String str) {
        return restTemplate.getForObject("http://cloud-nacos-provider/nacos/hello/"+str, String.class);
    }
}
