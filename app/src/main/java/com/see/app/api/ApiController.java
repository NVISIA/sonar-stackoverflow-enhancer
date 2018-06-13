package com.see.app.api;
import com.fasterxml.jackson.databind.JsonNode;
import com.see.app.RedisServer.SpringRedisManager;
import com.see.app.StackOverflow.*;


// import requirements for spring controller
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private static final String SO_API_URL_2_2 = "https://api.stackexchange.com/2.2/";
    private String SO_API_QUESTION(String query){return SO_API_URL_2_2 + "search?pagesize=1&order=desc&sort=votes&tagged=" + query + "&site=stackoverflow";}
    private String SO_API_ANSWER  (String query){return SO_API_URL_2_2 + "posts/" + query + "?site=stackoverflow&filter=withbody";}
    SpringRedisManager redisManager;

    public ApiController()
    {
        objectMapper = new ObjectMapper();
        redisManager = new SpringRedisManager();
    }

    @GetMapping("/call-api/{issue}")
    @ResponseBody
    public String getObject(@PathVariable(value="issue") String issue) {

        return createCall(issue);
    }


    // make a single API call using the RestTemplate
    private String createCall(String issue){

        //TODO: Check that there is no api object with the same issue
        //TODO: If such object exists, return it's result instead of calling API
        // make sure the issue is safe to pass for
        String issue_parsed = encodeURL(issue);
        //create API call to the website
        ResponseEntity<String> questionResponse = callGenericAPI(SO_API_QUESTION(issue_parsed));
        // create a java object based off the website's json result
        StackOverflowQuestion questionObject = mapResponseToQuestion(questionResponse.getBody());
        // check redis server for the answer id
        String answerID = questionObject.getAcceptedAnswerId().toString();
        //TODO redis server answer check
        String redisContent = SpringRedisManager.getAnswer(answerID);
        // if we have it, we return it, else make api call and return that
        if(redisContent != null)
        {
            return redisContent;
        }

        //now we know the issue, make another API call, but now get the
        ResponseEntity<String> answerResponse = callGenericAPI(SO_API_ANSWER(answerID));
        StackOverflowAnswer answerObject = mapResponseToAnswer(answerResponse.getBody());
        //TODO: Take the result and parse it out to individual fields (not single big string called result)
        //TODO: storing the java object into the redis since it doesn't exist inside of it

        return answerObject.getBody();
    }


    private ResponseEntity<String> callGenericAPI(String resourceUrl)
    {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build());
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
        assert(response.getStatusCode() == (HttpStatus.OK));
        return response;
    }

    // creating a generic mapping
    private StackOverflowQuestion mapResponseToQuestion(String response){
        try{
            JsonNode array = objectMapper.readValue(response, JsonNode.class);
            String content = array.get("items").get(0).toString();
            return  objectMapper.readValue(content, StackOverflowQuestion.class);
        }catch (Exception error) {
            throw new AssertionError("Can't read the values from the json");
        }
    }
    private StackOverflowAnswer mapResponseToAnswer(String response){
        try{
            JsonNode array = objectMapper.readValue(response, JsonNode.class);
            String content = array.get("items").get(0).toString();
            return  objectMapper.readValue(content, StackOverflowAnswer.class);
        }catch (Exception error) {
            throw new AssertionError("Can't read the values from the json");
        }
    }

    private String encodeURL(String word)
    {
        try {
            return URLEncoder.encode(word,"UTF-8");
        } catch (UnsupportedEncodingException ignored) {
            // usually ignored because UTF-8 is always supported, but just in case
            throw new AssertionError("UTF-8 is unknown");
        }
    }
}