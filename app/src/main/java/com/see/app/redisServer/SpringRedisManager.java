package com.see.app.redisServer;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import redis.clients.jedis.Jedis;

public class SpringRedisManager {

    private static boolean redisOnline;

    public SpringRedisManager() {

        redisOnline = checkConnection();
        if(redisOnline){
            System.out.println("-----------The redis connection is successful-----------");
        }
        else
        {
            System.err.println("-----------Reddis connection cannot be established-----------");
            System.err.println("Please check redis is running, and set up on the correct port");
        }
    }

    public static boolean checkConnection(){
        Jedis jedis = new Jedis("localhost"); // for now we assume redis is running on the same IP
        String output;
        try {
           output = jedis.ping();
        }
        catch (Exception error){
            return false;
        }
        boolean checkOnline = (output.contains("PONG"));
        System.out.println("Redis server status: " + checkOnline);
        if(checkOnline){
            System.out.println("-----------The redis connection is successful-----------");
        }
        else
        {
            System.err.println("-----------Reddis connection cannot be established-----------");
            System.err.println("Please check redis is running, and set up on the correct port");
        }
        return checkOnline;
    }

    public static void setValue(String key, String content) {
        if(redisOnline == false)
        {
            System.err.println("Redis Server is currently offline, cannot set the values . . . Please check redis is running");
            return; // leave early, we cannot set values, sorry
        }
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
                SpringRedisConfig.class);
        try {
            RedisTemplate redisTemplate = (RedisTemplate) ctx.getBean("redisTemplate");
            ValueOperations values = redisTemplate.opsForValue();
            values.set(key, content);
        } finally {
            ctx.close();
        }
    }

    public static String getValue(String key) {
        if(redisOnline == false)
        {
            System.err.println("Redis Server is currently offline, cannot get the values . . . Please check redis is running");
            return null; // leave early, we cannot set values, sorry
        }
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
                SpringRedisConfig.class);
        String redisValue = null;
        try {
            RedisTemplate redisTemplate = (RedisTemplate) ctx.getBean("redisTemplate");
            if (redisTemplate.hasKey(key)) {
                ValueOperations values = redisTemplate.opsForValue();
                redisValue = values.get(key).toString();
            }
        } finally {
            ctx.close();
        }
        return redisValue;

    }
}
