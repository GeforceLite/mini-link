package com.minilink;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@MapperScan("com.minilink.mapper")
@SpringBootApplication
public class MiniLinkUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniLinkUserApplication.class, args);
    }
}
