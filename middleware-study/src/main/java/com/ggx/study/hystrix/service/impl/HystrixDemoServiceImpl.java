package com.ggx.study.hystrix.service.impl;

import com.ggx.study.hystrix.service.HystrixDemoService;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class HystrixDemoServiceImpl implements HystrixDemoService {

    private RestTemplate restTemplate;

    public HystrixDemoServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(groupKey = "businessQuery",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")},
            fallbackMethod = "queryFallback")
    @Override
    public String queryWithThreadPool(String userId) {
        URI uri = URI.create("http://127.0.0.1:8080/business/slowQuery/2");
        return this.restTemplate.getForObject(uri, String.class);
    }

    public String queryFallback(String userId) {
        HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true);
        return "fallback";
    }
}
