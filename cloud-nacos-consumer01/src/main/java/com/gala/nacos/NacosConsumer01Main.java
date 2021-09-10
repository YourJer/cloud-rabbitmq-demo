package com.gala.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author YJ
 * @date 2021/9/10
 * @description NacosConsumer01Main
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosConsumer01Main {

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumer01Main.class);
    }
}
