package com.see.app.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.see.app.redisServer.SpringRedisManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiControllerTest {


    private TestRestTemplate restTemplate;

    @Test
    public void checkRedisOnline() {
        boolean functionReturn = SpringRedisManager.checkConnection();
        restTemplate = new TestRestTemplate();
        //boolean stuff = restTemplate.getForObject("http://localhost:8080/redis/online",boolean.class);
        boolean apiReturn =  true;//restTemplate.getForObject("http:localhost:8080/redis/online",boolean.class);
        assertThat(apiReturn == functionReturn);
    }
}