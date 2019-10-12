package com.rabbit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * @Author:zhuzhou
 * @Date: 2019/7/24---14:26
 **/
@SpringBootApplication
public class RabbitMaApplication {
    @Value("#{${hu.ra}}")
    private   Map<String, String> map;

    public static void main(String[] args) {
        SpringApplication.run(RabbitMaApplication.class, args);

    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                String[] names = applicationContext.getBeanDefinitionNames();
                System.out.println(map+"\t =============");
                for (String name : names) {
                    //System.out.println(name);
                }
            }
        };
    }
}
