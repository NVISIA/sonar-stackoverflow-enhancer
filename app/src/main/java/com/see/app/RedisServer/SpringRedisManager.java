package com.see.app.RedisServer;

import com.see.app.StackOverflow.StackOverflowAnswer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;


public class SpringRedisManager {

    public SpringRedisManager() {
        System.out.println("-----------The redis manager has been created successfully-----------");
    }

    public static void setAnswer(String key, String content){
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

    public String getAnswer(String key)
    {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
                SpringRedisConfig.class);
        String redisValue;
        try {
            RedisTemplate redisTemplate = (RedisTemplate) ctx.getBean("redisTemplate");
            ValueOperations values = redisTemplate.opsForValue();
            redisValue = values.get(key).toString();
        }
        finally {
            ctx.close();
        }
        return redisValue;

    }

    public String checkRedisServer(StackOverflowAnswer answerElem)
    {
        String key = answerElem.getPostId().toString();
        String content = answerElem.getBody();
        // TODO: check if the server already contains the key

        //TODO: if key exists, return it

        // TODO: the entry doesn't exist, return something else?

        return null;
    }
}
