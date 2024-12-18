package com.minilink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class MiniLinkGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniLinkGatewayApplication.class, args);
    }

}
