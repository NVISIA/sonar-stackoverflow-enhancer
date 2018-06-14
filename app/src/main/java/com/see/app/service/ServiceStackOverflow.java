package com.see.app.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.see.app.redisServer.SpringRedisManager;
import com.see.app.stackOverflow.StackOverflowAnswer;
import com.see.app.stackOverflow.StackOverflowQuestion;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class ServiceStackOverflow {

    private static final String SO_API_URL_2_2 = "https://api.stackexchange.com/2.2/";
    ObjectMapper objectMapper;
    SpringRedisManager redisManager;

    @Autowired
    public ServiceStackOverflow() {
        objectMapper = new ObjectMapper();
        redisManager = new SpringRedisManager();
    }

    private String SO_API_QUESTION(String query) {
        return SO_API_URL_2_2 + "search?pagesize=1&order=desc&sort=votes&tagged=" + query + "&site=stackoverflow";
    }

    private String SO_API_ANSWER(String query) {
        return SO_API_URL_2_2 + "posts/" + query + "?site=stackoverflow&filter=withbody";
    }

    public String createCall(String issue) {
        StackOverflowQuestion questionObject = createQuestion(issue);
        String answerID = questionObject.getAcceptedAnswerId().toString();
        StackOverflowAnswer answerObject = createAnswer(answerID);
        return answerObject.getBody();
    }

    private StackOverflowQuestion createQuestion(String issue) {
        StackOverflowQuestion questionObject;
        String redisContentQuestion = SpringRedisManager.getValue(issue);
        if (redisContentQuestion == null) {
            questionObject = callQuestionAPI(issue);
            SpringRedisManager.setValue(issue, questionObject.getAcceptedAnswerId().toString());
        } else {
            questionObject = new StackOverflowQuestion();
            questionObject.setAcceptedAnswerId(Integer.valueOf(redisContentQuestion));
        }
        return questionObject;
    }

    private StackOverflowAnswer createAnswer(String answerID) {
        String redisContent = SpringRedisManager.getValue(answerID);
        StackOverflowAnswer answerObject;
        if (redisContent == null) {
            answerObject = callAnswerAPI(answerID);
        } else {
            answerObject = new StackOverflowAnswer();
            answerObject.setBody(redisContent);
        }

        return answerObject;
    }

    private StackOverflowQuestion callQuestionAPI(String issue) {
        String issue_parsed = encodeURL(issue);
        ResponseEntity<String> questionResponse = callGenericAPI(SO_API_QUESTION(issue_parsed));
        StackOverflowQuestion questionObject = mapResponseToQuestion(questionResponse.getBody());
        return questionObject;
    }

    private StackOverflowAnswer callAnswerAPI(String answerID) {
        ResponseEntity<String> answerResponse = callGenericAPI(SO_API_ANSWER(answerID));
        StackOverflowAnswer answerObject = mapResponseToAnswer(answerResponse.getBody());
        return answerObject;
    }

    private StackOverflowQuestion mapResponseToQuestion(String response) {
        try {
            return objectMapper.readValue(getContent(response), StackOverflowQuestion.class);
        } catch (Exception error) {
            throw new AssertionError("Can't map json to a java object");
        }
    }

    private StackOverflowAnswer mapResponseToAnswer(String response) {
        try {
            return objectMapper.readValue(getContent(response), StackOverflowAnswer.class);
        } catch (Exception error) {
            throw new AssertionError("Can't map json to a java object");
        }
    }

    private String getContent(String response) {
        try {
            JsonNode array = objectMapper.readValue(response, JsonNode.class);
            return array.get("items").get(0).toString();
        } catch (Exception exception) {
            throw new AssertionError("Can't read the values from the json");
        }
    }

    private ResponseEntity<String> callGenericAPI(String resourceUrl) {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build());
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
        assert (response.getStatusCode() == (HttpStatus.OK));
        return response;
    }

    private String encodeURL(String word) {
        try {
            return URLEncoder.encode(word, "UTF-8");
        } catch (UnsupportedEncodingException ignored) {
            // usually ignored because UTF-8 is always supported, but just in case
            throw new AssertionError("UTF-8 is unknown");
        }
    }
}
