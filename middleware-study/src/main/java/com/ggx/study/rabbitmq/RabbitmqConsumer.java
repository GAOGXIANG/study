package com.ggx.study.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RabbitmqConsumer {


    @RabbitListener(queues = "rabbitmq:queue:direct:test")
    public void processDirectTest(String data, Channel channel, Message message){
        System.out.println("test receive msg:" + data);
        try {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "rabbitmq:queue:direct:production")
    public void processDirectProduction(String data, Channel channel, Message message){
        System.out.println("production receive msg :" + data);
        try{
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "rabbitmq:queue:direct:dead:test")
    public void processDirectDeadLetter(String data, Channel channel, Message message){

        System.out.println("receive dead letter:" + data + ". routingKey:" + message.getMessageProperties().getReceivedRoutingKey());
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
