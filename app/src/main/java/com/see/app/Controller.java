package com.see.app;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Controller implements CommandLineRunner {
    // TODO: when we have multple API's, make sure it knows which one is being called
    @Override
    public void run(String... strings) throws Exception{
        String url = "http://localhost:8080/stuff";
        System.out.print("Stuff is happening");

    }

}
