package com.see.app.controller;

import com.see.app.service.ServiceSonar;
import com.see.app.service.ServiceStackOverflow;
import com.see.app.sonar.Issue;
import com.see.app.sonar.SonarObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ApiController {

    @GetMapping("/stack-api/{issue}")
    @ResponseBody
    public String stackAPI(@PathVariable(value = "issue") String issue) {
        ServiceStackOverflow soAPI = new ServiceStackOverflow();
        return soAPI.createCall(issue);
    }

    @GetMapping("/sonar-api")
    @ResponseBody
    public List<Issue> sonarAPI() {
        return ServiceSonar.createCall().getIssues();
    }


}