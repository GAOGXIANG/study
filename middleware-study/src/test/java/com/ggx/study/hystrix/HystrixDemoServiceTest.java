package com.ggx.study.hystrix;

import com.ggx.study.ServerApplication;
import com.ggx.study.hystrix.service.HystrixDemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
public class HystrixDemoServiceTest {

    @Autowired
    private HystrixDemoService hystrixDemoService;

    @Test
    public void testFallback(){
        while(true){
            System.out.println(hystrixDemoService.queryWithThreadPool("ggx"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

