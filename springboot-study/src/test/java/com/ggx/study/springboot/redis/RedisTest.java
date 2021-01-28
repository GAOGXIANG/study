package com.ggx.study.springboot.redis;


import com.ggx.springboot.study.ServerApplication;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void setValue(){
        redisTemplate.opsForValue().set("test", "true");
        System.out.println("设置key test的值为" + redisTemplate.opsForValue().get("test"));
    }


    @Test
    public void testString(){
        stringRedisTemplate.opsForValue().set("AMD", "AMD");
        stringRedisTemplate.opsForValue().setIfAbsent("AMD", "YES", 10, TimeUnit.SECONDS);
        //stringRedisTemplate.opsForValue().append("AMD", ", YES!");
        //System.out.println(stringRedisTemplate.opsForValue().get("AMD").equals("AMD, YES!"));
        //System.out.println("setIfAbsent:" + stringRedisTemplate.opsForValue().setIfAbsent("test", "true",10, TimeUnit.SECONDS));
        //System.out.println("test TTL:" + stringRedisTemplate.getExpire("test", TimeUnit.MILLISECONDS));
        //System.out.println(stringRedisTemplate.opsForValue().setIfPresent("intel", "NO"));
        //stringRedisTemplate.opsForValue().set("intel", "YES");
        //System.out.println(stringRedisTemplate.opsForValue().setIfPresent("intel", "NO"));
//        Map<String, String> setMap = new HashMap<>();
//        setMap.put("M1", "YES!");
//        setMap.put("AMD", "NO!");
//        System.out.println(stringRedisTemplate.opsForValue().multiSetIfAbsent(setMap));
//        System.out.println(redisTemplate.opsForValue().get("test") + "  size:" + redisTemplate.opsForValue().size("test"));
        System.out.println(stringRedisTemplate.opsForValue().size("AMD"));
    }

    @Test
    public void testHash(){
//        stringRedisTemplate.opsForHash().put("videoCard", "AMD", "YES!");
//        System.out.println(stringRedisTemplate.opsForHash().hasKey("videoCard", "AMD"));
//        System.out.println(stringRedisTemplate.opsForHash().hasKey("videoCard", "INTEL"));
//        Map<String, String> setMap = new HashMap<>();
//        setMap.put("M1", "NO!");
//        setMap.put("INTEL", "NO!");
//        setMap.put("NVIDIA", "YES!");
//        stringRedisTemplate.opsForHash().putAll("videoCard", setMap);
//        List<Object> objectList = stringRedisTemplate.opsForHash().multiGet("videoCard", Lists.newArrayList("AMD", "INTEL"));
//        objectList.forEach(object -> System.out.println(object.toString()));
        System.out.println(stringRedisTemplate.opsForHash().size("videoCard"));
        List<Object> list = stringRedisTemplate.opsForHash().multiGet("videoCard", Lists.newArrayList("AMD", "INTEL", "NVIDIA"));
        list.forEach(object -> System.out.println(object.toString()));
    }

    @Test
    public void testList(){
//        stringRedisTemplate.opsForList().leftPushAll("videoCardBrand", "AMD", "NVIDIA", "INTEL");
//        System.out.println(stringRedisTemplate.opsForList().leftPushIfPresent("videoCardBrand", "INTEL"));
        stringRedisTemplate.opsForList().leftPop("videoCardBrand");
        stringRedisTemplate.opsForList().set("videoCardBrand", 0, "ARM");
        stringRedisTemplate.opsForList().rightPopAndLeftPush("videoCardBrand", "NVIDIA");
    }

    @Test
    public void testSet(){
//        stringRedisTemplate.opsForSet().add("videoCardSet", "AMD", "NVIDIA", "INTEL", "APPLE", "KIRIN");
//        stringRedisTemplate.opsForSet().add("cpuSet", "AMD", "SNAPDRAGON", "INTEL", "APPLE", "KIRIN");
        Set<String> set = stringRedisTemplate.opsForSet().intersect("videoCardSet", "cpuSet");
        System.out.println("=============intersect===================");
        set.forEach(string -> System.out.println(string));
        set = stringRedisTemplate.opsForSet().difference("videoCardSet", "cpuSet");
        System.out.println("=============difference===================");
        set.forEach(string -> System.out.println(string));
        set = stringRedisTemplate.opsForSet().union("videoCardSet", "cpuSet");
        System.out.println("=============union===================");
        set.forEach(string -> System.out.println(string));
//        stringRedisTemplate.opsForSet().
    }

    @Test
    public void testZSet(){
        Set<ZSetOperations.TypedTuple<String>> set = new HashSet<>();
        set.add(new DefaultTypedTuple<String>("INTEL", 20D));
        set.add(new DefaultTypedTuple<String>("KIRIN", 60D));
        set.add(new DefaultTypedTuple<String>("APPLE", 70D));
        set.add(new DefaultTypedTuple<String>("AMD", 85D));
        set.add(new DefaultTypedTuple<String>("NVIDIA", 90D));
        stringRedisTemplate.opsForZSet().add("videoCardRankSet", set);
        stringRedisTemplate.opsForZSet().remove("videoCardRankSet", "INTEL");
        Set<String> videoCardRankSet = stringRedisTemplate.opsForZSet().range("videoCardRankSet", 0, 2);
        System.out.println("=================获取前三个元素================");
        videoCardRankSet.forEach(string -> System.out.println(string));
        System.out.println("=================获取前两个元素================");
        videoCardRankSet = stringRedisTemplate.opsForZSet().reverseRange("videoCardRankSet", 0, 1);
        videoCardRankSet.forEach(string -> System.out.println(string));
    }
}
