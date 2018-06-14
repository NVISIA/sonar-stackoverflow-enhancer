package com.see.app.controller;

import com.see.app.service.ServiceStackOverflow;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {

    @GetMapping("/call-api/{issue}")
    @ResponseBody
    public String getObject(@PathVariable(value = "issue") String issue) {
        ServiceStackOverflow soAPI = new ServiceStackOverflow();
        return soAPI.createCall(issue);
    }
}