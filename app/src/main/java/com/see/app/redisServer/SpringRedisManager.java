package com.see.app.redisServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import redis.clients.jedis.Jedis;

public class SpringRedisManager {

    //TODO: get values from configuration file
    private static String springRedisHost = "localhost";
    private static int springRedisPort = 6379;
    private static boolean redisOnline = false;
    private static final Jedis jedis = new Jedis(springRedisHost);
    private static final Logger logger = LoggerFactory.getLogger(SpringRedisManager.class);

    private SpringRedisManager(){}

    public static void initiateConnection()
    {
        redisOnline = checkConnection();
    }

    public static boolean checkConnection(){
        String output;
        try {
           output = jedis.ping();
        }
        catch (Exception error){
            return false;
        }
        boolean checkOnline = (output.contains("PONG"));
        if(checkOnline){
            printConnectionSuccess();
        }
        else
        {
            printConncetionError();
        }
        return checkOnline;
    }

    private static void printConncetionError()
    {
        logger.error("-----------Reddis connection cannot be established-----------\n" +
                "Please check redis is running, and set up on the correct port");
    }

    private static void printConnectionSuccess()
    {
        logger.info("-----------The redis connection is successful on " +
                springRedisHost + ":"+springRedisPort+ "-----------");
    }

    public static String getInfo()
    {
        if(!redisOnline){return "Redis is offline, no information to give . . .";}
        return jedis.info();
    }

    public static void setValue(String key, String content) {
        if(!redisOnline)
        {
            printConncetionError();
             return;
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
        if(!redisOnline)
        {
            printConncetionError();
            return null;
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
