package com.minilink;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.minilink.mapper")
public class MiniLinkCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniLinkCoreApplication.class, args);
    }
}
