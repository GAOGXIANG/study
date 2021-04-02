package com.ggx.study.hystrix.controller;

import com.ggx.study.hystrix.service.HystrixDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixController {

    @Autowired
    private HystrixDemoService hystrixDemoService;

    @GetMapping(value = "/demo/search")
    public String search(){
        return hystrixDemoService.queryWithThreadPool("");
    }
}
