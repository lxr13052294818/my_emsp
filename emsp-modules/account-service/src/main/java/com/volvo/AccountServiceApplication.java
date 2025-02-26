package com.volvo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 账户管理
 *
 * @author lxr
 * @date 2025/2/23
 */
@SpringBootApplication(scanBasePackages = "com.volvo")
@EnableDiscoveryClient
@MapperScan("com.volvo.**.mapper")
@EnableFeignClients(basePackages = "com.volvo.client")
public class AccountServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
}
