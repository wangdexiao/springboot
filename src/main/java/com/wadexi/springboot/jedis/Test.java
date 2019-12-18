package com.wadexi.springboot.jedis;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = context.getBean("restTemplate",RedisTemplate.class);
        StringRedisTemplate stringRedisTemplate = context.getBean("stringRedisTemplate",StringRedisTemplate.class);
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForHash().put("hash","field","hvalues");
        redisTemplate.opsForHash().put("hash","field2","hvalues");

        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {

                operations.opsForValue().set("name", "王德晓");
                operations.opsForValue().set("name2", "wadexi");
                return null;

            }
        });


        testList(stringRedisTemplate);

    }

    private static void testList(StringRedisTemplate stringRedisTemplate) {

        stringRedisTemplate.opsForList().leftPushAll("list1", "v2", "v4", "v6", "v8", "v10");
        stringRedisTemplate.opsForList().rightPushAll("list2", "v1", "v2", "v3", "v4", "v5");
        BoundListOperations<String, String> list2 = stringRedisTemplate.boundListOps("list2");
        list2.leftPush("v0");
        Long size = list2.size();
        List<String> range = list2.range(0, size - 2);
        System.out.println(range.toString());
    }
}
