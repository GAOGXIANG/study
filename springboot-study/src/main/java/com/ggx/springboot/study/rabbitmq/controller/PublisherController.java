package com.ggx.springboot.study.rabbitmq.controller;

import com.ggx.springboot.study.rabbitmq.RabbitmqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
public class PublisherController {

    @Autowired
    private RabbitmqProducer rabbitmqProducer;

    @RequestMapping(value = "/publish/{exchange}/{routingKey}/{message}", method = RequestMethod.GET)
    public void publishMessage(@PathVariable(value = "exchange") String exchange, @PathVariable String routingKey, @PathVariable String message){

        rabbitmqProducer.sendMsg(message, exchange, routingKey);
    }
}
