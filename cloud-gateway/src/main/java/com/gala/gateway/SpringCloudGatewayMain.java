package com.gala.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author YJ
 * @date 2021/9/10
 * @description SpringCloudGatewayMain
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudGatewayMain {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayMain.class);
    }
}
