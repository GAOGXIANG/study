package com.ggx.springboot.study.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendMsg(String msg){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setExpiration("50000");//设置消息的过期时间，单位毫秒
//        messageProperties.setDelay();
        Message message = new Message(msg.getBytes(), messageProperties);
        rabbitTemplate.send(RabbitmqConfiguration.DIRECT_TEST_EXCHANGE, RabbitmqConfiguration.DIRECT_TEST_ROUTING_KEY, message);
    }

    public void sendMsg(String msg, String exchange, String routingKey){
        switch(exchange){
            case "direct":
                exchange = RabbitmqConfiguration.DIRECT_TEST_EXCHANGE;
                break;
            case "fanout":
                break;
        }
        MessageProperties messageProperties = new MessageProperties();
        Message message = new Message(msg.getBytes(), messageProperties);
        rabbitTemplate.send(exchange, routingKey, message);
    }
}
