package com.ggx.study.springboot.mybatis;

import com.ggx.springboot.study.ServerApplication;
import com.ggx.springboot.study.mybatis.mapper.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
public class MybatisTest {

    @Autowired
    private TestMapper testMapper;

    @Test
    public void testGetById(){
        com.ggx.springboot.study.mybatis.bean.Test test = testMapper.getById(10);
        System.out.println(test);
    }

    @Test
    public void testMapperXml(){
        com.ggx.springboot.study.mybatis.bean.Test test = new com.ggx.springboot.study.mybatis.bean.Test();
        test.setId(10);
        test.setA(10);
        test.setB(10);
        testMapper.updateTest(test);
    }

}
