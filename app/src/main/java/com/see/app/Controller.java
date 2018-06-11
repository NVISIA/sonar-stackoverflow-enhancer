package com.see.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Controller implements CommandLineRunner {
    // TODO: when we have multple API's, make sure it knows which one is being called
    @Override
    public void run(String... strings) throws Exception{
        String url = "http://localhost:8080/stuff";
        System.out.print("Application has launched . . . ");

    }

}
