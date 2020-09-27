package com.ggx.springboot.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableTransactionManagement
@SpringBootApplication
public class ServerApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ServerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ServerApplication.class);
        springApplication.run(args);
    }
}
