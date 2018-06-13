package com.see.app.api;
import com.see.app.RedisServer.SpringRedisManager;
import com.see.app.StackOverflow.*;

// import requirements for spring controller
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
// import requirements for online API request
import org.apache.http.impl.client.HttpClientBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;

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

    private String createCall(String issue){
        StackOverflowQuestion questionObject = createQuestion(issue);
        String answerID = questionObject.getAcceptedAnswerId().toString();
        StackOverflowAnswer answerObject = createAnswer(answerID);
        return answerObject.getBody();
    }

    private StackOverflowQuestion createQuestion(String issue){
        StackOverflowQuestion questionObject;
        String redisContentQuestion = SpringRedisManager.getValue(issue);
        // if we have it, we return it, else make api call and return that
        if(redisContentQuestion == null)
        {
            // it doesn't exist, make the call
            questionObject = callQuestionAPI(issue);
            // save it for later use
            SpringRedisManager.setValue(issue,questionObject.getAcceptedAnswerId().toString());
        }
        else
        {
            questionObject = new StackOverflowQuestion();
            questionObject.setAcceptedAnswerId(Integer.valueOf(redisContentQuestion));
        }
        return questionObject;
    }

    private StackOverflowAnswer createAnswer(String answerID)
    {
        String redisContent = SpringRedisManager.getValue(answerID);
        StackOverflowAnswer answerObject;
        // if we have it, we return it, else make api call and return that
        if(redisContent == null)
        {
            answerObject = callAnswerAPI(answerID);
        }
        else {
            answerObject = new StackOverflowAnswer();
            answerObject.setBody(redisContent);
        }

        return answerObject;
    }

    private StackOverflowQuestion callQuestionAPI(String issue)
    {
        String issue_parsed = encodeURL(issue);
        //create API call to the website
        ResponseEntity<String> questionResponse = callGenericAPI(SO_API_QUESTION(issue_parsed));
        // create a java object based off the website's json result
        StackOverflowQuestion questionObject = mapResponseToQuestion(questionResponse.getBody());
        return questionObject;
    }

    private StackOverflowAnswer callAnswerAPI(String answerID)
    {
        ResponseEntity<String> answerResponse = callGenericAPI(SO_API_ANSWER(answerID));
        StackOverflowAnswer answerObject = mapResponseToAnswer(answerResponse.getBody());
        return answerObject;
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