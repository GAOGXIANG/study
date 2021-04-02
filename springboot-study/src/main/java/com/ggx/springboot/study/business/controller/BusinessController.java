package com.ggx.springboot.study.business.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {

    @GetMapping(value = "/business/slowQuery/{delay}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String slowQuery(@PathVariable(value = "delay") Integer delay){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
