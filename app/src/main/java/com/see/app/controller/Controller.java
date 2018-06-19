package com.see.app.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Controller implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.print("Application has launched . . . ");
    }

}
