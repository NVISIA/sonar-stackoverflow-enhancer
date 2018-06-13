package com.see.app.RedisServer;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class SpringRedisManager {

    public SpringRedisManager() {
        System.out.println("-----------The redis manager has been created successfully-----------");
    }

    public static void setValue(String key, String content){
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
                SpringRedisConfig.class);
        try {
            RedisTemplate redisTemplate = (RedisTemplate) ctx.getBean("redisTemplate");
            ValueOperations values = redisTemplate.opsForValue();
            values.set(key,content);
        } finally {
            ctx.close();
        }
    }

    public static String getValue(String key)
    {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
                SpringRedisConfig.class);
        String redisValue = null;
        try {
            RedisTemplate redisTemplate = (RedisTemplate) ctx.getBean("redisTemplate");
            if(redisTemplate.hasKey(key))
            {
                ValueOperations values = redisTemplate.opsForValue();
                redisValue = values.get(key).toString();
            }
        }
        finally {
            ctx.close();
        }
        return redisValue;

    }
}
