package com.ggx.springboot.study.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfiguration {
    public static final String DIRECT_TEST_EXCHANGE = "rabbitmq:exchange:direct:test";
    public static final String DIRECT_TEST_ROUTING_KEY = "RABBITMQ_DIRECT_TEST_KEY";
    public static final String DIRECT_TEST_DEAD_LETTER_EXCHANGE = "rabbitmq:exchange:direct:dead:test";
    public static final String DIRECT_TEST_DEAD_LETTER_QUEUE = "rabbitmq:queue:direct:dead:test";
    public static final String DIRECT_TEST_DEAD_ROUTING_KEY = "RABBITMQ_DIRECT_DEAD_TEST_KEY";
    public static final String DIRECT_PRODUCTION_ROUTING_KEY = "RABBITMQ_DIRECT_PRODUCTION_KEY";

    @Bean
    public DirectExchange testDirectExchange(AmqpAdmin admin){
        DirectExchange exchange = new DirectExchange(DIRECT_TEST_EXCHANGE);
        admin.declareExchange(exchange);
        return exchange;
    }

    @Bean
    public Queue testDirectQueue(AmqpAdmin admin){
        String queueName = "rabbitmq:queue:direct:test";
//        admin.deleteQueue(queueName);
        Queue queue = new Queue(queueName);
        queue.addArgument("x-dead-letter-exchange", DIRECT_TEST_DEAD_LETTER_EXCHANGE);
        queue.addArgument("x-dead-letter-routing-key", DIRECT_TEST_DEAD_ROUTING_KEY);
        admin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue productionDirectQueue(AmqpAdmin admin){
        String queueName = "rabbitmq:queue:direct:production";
        Queue queue = new Queue(queueName);
        admin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Binding bindingTestExchangeQueue(AmqpAdmin admin){
        return BindingBuilder.bind(testDirectQueue(admin)).to(testDirectExchange(admin)).with(DIRECT_TEST_ROUTING_KEY);
    }

    @Bean
    public Binding bindingProductionExchangeQueue(AmqpAdmin admin){
        return BindingBuilder.bind(productionDirectQueue(admin)).to(testDirectExchange(admin)).with(DIRECT_PRODUCTION_ROUTING_KEY);
    }

    @Bean
    public DirectExchange directDeadExchange(AmqpAdmin admin){
        DirectExchange exchange = new DirectExchange(DIRECT_TEST_DEAD_LETTER_EXCHANGE);
        admin.declareExchange(exchange);
        return exchange;
    }

    @Bean
    public Queue directDeadQueue(AmqpAdmin admin){
        Queue queue = new Queue(DIRECT_TEST_DEAD_LETTER_QUEUE);
//        queue.addArgument("x-message-ttl", 60000);  设置队列的消息过期时间，单位毫秒
//        queue.addArgument("x-max-priority", 10);  设置队列的最大优先级
        admin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Binding bindingDealExchangeQueue(AmqpAdmin admin){
        return BindingBuilder.bind(directDeadQueue(admin)).to(directDeadExchange(admin)).with(DIRECT_TEST_DEAD_ROUTING_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) ->{
            if(ack) {
                System.out.println("message send success;");
            }else{
                System.out.println("message send error:" + cause);
            }
        });
        rabbitTemplate.setReturnCallback((Message m, int replyCode, String replyText, String exchange, String routingKey) ->{
            System.out.println("message send error，msg:" + new String(m.getBody()) + " , replyCode:" + replyCode
                    + " ,replyText:" + replyText + " ,exchange:" + exchange + " ,routingKey:" + routingKey);
        });
        return rabbitTemplate;
    }
}

