package com.ggx.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrix
@EnableHystrixDashboard
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ServerApplication.class);
        springApplication.run(args);
    }
}
