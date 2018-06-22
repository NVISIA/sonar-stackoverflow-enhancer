package com.see.app.redisServer;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.*;

public class SpringRedisManagerTest {

    @Test
    public void initiateConnection() {
        assert SpringRedisManager.checkConnection() == true;
    }

    @Test
    public void checkConnection() {
        Jedis jedis = new Jedis("localhost");
        assert jedis.ping().contains("PONG");
    }
}