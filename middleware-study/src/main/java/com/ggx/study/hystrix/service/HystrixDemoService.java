package com.ggx.study.hystrix.service;

public interface HystrixDemoService {

    String queryWithThreadPool(String userId);
}
