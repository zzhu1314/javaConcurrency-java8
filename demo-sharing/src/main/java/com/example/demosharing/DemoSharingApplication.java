package com.example.demosharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { io.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration.class})
public class DemoSharingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSharingApplication.class, args);
    }

}
