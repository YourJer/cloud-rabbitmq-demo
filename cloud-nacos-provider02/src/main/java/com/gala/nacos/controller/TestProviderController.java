package com.gala.nacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YJ
 * @date 2021/9/10
 * @description TestProviderController
 */
@Slf4j
@RestController
@RequestMapping("/nacos")
public class TestProviderController {

    @GetMapping("/hello/{str}")
    public String getHello(@PathVariable String str) {
        return "ni hao a ! I m second "+str;
    }
}
