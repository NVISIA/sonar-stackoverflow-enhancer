package com.see.app.api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ApiController {
    // make an API call using the RestTemplate
    public static void makeApiCall(String values)
    {
        // later on we'll pass Api objects to make API calls
        ApiObject issue = new ApiObject("This is some issue the user has");

        //create API call best off the resttemplate
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "https://jsonplaceholder.typicode.com/posts";
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl + "/1", String.class);
        // assume no errors in the network
        assert(response.getStatusCode() == (HttpStatus.OK));
        System.out.println("Api call finished with: " + response);

        // now we save the issue to the ApiObject as a string
        issue.setResult(response.toString());
    }

    public String createCall()
    {
        // based off information, we'll call the api, for now return dummy value
        return "Dummy output";
    }
}