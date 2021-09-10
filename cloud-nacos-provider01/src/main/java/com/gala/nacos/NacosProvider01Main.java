package com.gala.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author YJ
 * @date 2021/9/10
 * @description NacosProvider01Main
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosProvider01Main {
    public static void main(String[] args) {
        SpringApplication.run(NacosProvider01Main.class);
    }
}
