package com.minilink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MiniLinkCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniLinkCoreApplication.class, args);
    }
}
