package com.see.app.api;

// import requirements for spring controller
import com.fasterxml.jackson.databind.ObjectMapper;
import com.see.app.StackOverflow.StackOverflowObject;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
// import requirements for online API request
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class ApiController {

    ObjectMapper objectMapper;
    private static final String API_URL_2_2 = "https://api.stackexchange.com/2.2/";

    public ApiController()
    {
        objectMapper = new ObjectMapper();
    }

    @GetMapping("/call-api/{issue}")
    @ResponseBody
    public String getObject(@PathVariable(value="issue") String issue) {
        return createCall(issue).toString();
    }

    // make a single API call using the RestTemplate
    private StackOverflowObject createCall(String issue){

        //TODO: Check that there is no api object with the same issue
        //TODO: If such object exists, return it's result instead of calling API
        // make sure the issue is safe to pass for
        String issue_parsed = encodeURL(issue);
        //create API call to the website
        ResponseEntity<String> response = callSOApi(issue_parsed);
        // create a java object based off the website's json result
        StackOverflowObject issueObject = getFormatedObject(response.getBody());

        //TODO: Take the result and parse it out to individual fields (not single big string called result)
        // TODO: Store the issue object to be compared later

        return issueObject;
    }

    private ResponseEntity<String> callSOApi(String issue)
    {
        String resourceUrl = API_URL_2_2 + "search?order=desc&sort=votes&tagged="+issue+"&site=stackoverflow";
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build());
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
     ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
        assert(response.getStatusCode() == (HttpStatus.OK));
        // TODO: any filtering on the object, such as taking out not needed fields,values, etc.
        return response;
    }

    private StackOverflowObject getFormatedObject(String response){

        StackOverflowObject issueObject = null;
        try{
            issueObject = objectMapper.readValue(response, StackOverflowObject.class);
        }catch (Exception error)
        {
            throw new AssertionError("Can't read the values from the json");
        }
        return issueObject;
    }

    private String encodeURL(String word)
    {
        String word_parsed = null;
        try {
            word_parsed = URLEncoder.encode(word,"UTF-8");
        } catch (UnsupportedEncodingException ignored) {
            // usually ignored because UTF-8 is always supported, but just in case
            throw new AssertionError("UTF-8 is unknown");
        }
        return word_parsed;
    }
}