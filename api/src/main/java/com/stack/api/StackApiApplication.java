package com.stack.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StackApiApplication {

    public static void main(String[] args) {
       // SpringApplication.run(ApiApplication.class, args);
        makeApiCall();
    }


    // make an API call using the RestTemplate
    public static void makeApiCall()
    {

        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "https://jsonplaceholder.typicode.com/posts";
        ResponseEntity<String> response
                = restTemplate.getForEntity(resourceUrl + "/1", String.class);
        // assume no errors in the network
        assert(response.getStatusCode() == (HttpStatus.OK));

        System.out.println("Api call finished with: " + response);

    }

}
