package com.see.app.controller;

import com.see.app.redisServer.SpringRedisManager;
import com.see.app.service.ServiceSonar;
import com.see.app.service.ServiceStackOverflow;
import com.see.app.sonar.Issue;
import com.see.app.stackOverflow.StackOverflowAnswer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ApiController {

    @GetMapping("/stack-api/{issue}")
    @ResponseBody
    public StackOverflowAnswer stackAPI(@PathVariable(value = "issue") String issue) {
        ServiceStackOverflow soAPI = new ServiceStackOverflow();
        return soAPI.createCall(issue);
    }

    @GetMapping("/sonar-api")
    @ResponseBody
    public List<Issue> sonarAPI() {
        return ServiceSonar.createCall().getIssues();
    }

    // make sure that redis server is running
    @GetMapping("/redis/online")
    @ResponseBody
    public boolean checkRedisOnline()
    {
        SpringRedisManager springRedisManager = new SpringRedisManager();
        return springRedisManager.checkConnection();
    }

    @GetMapping("/redis/info")
    @ResponseBody
    public String getRedisInfo()
    {
        SpringRedisManager springRedisManager = new SpringRedisManager();
        return springRedisManager.getInfo();
    }
}