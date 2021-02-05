package com.ggx.study.springboot.rabbitmq;

import com.ggx.springboot.study.ServerApplication;
import com.ggx.springboot.study.rabbitmq.RabbitmqProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
public class RabbitmqTest {

    @Autowired
    private RabbitmqProducer rabbitmqProducer;

    @Test
    public void testProduce() throws InterruptedException {
        for(int i = 0; i < 9; i++) {
            rabbitmqProducer.sendMsg("hello, rabbitmq!" + i);
        }
        Thread.sleep(30000);
    }
}
