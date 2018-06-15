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

    SpringRedisManager redisManager;

    @Autowired
    public ServiceStackOverflow() {
        redisManager = new SpringRedisManager();
    }

    private String SO_API_QUESTION(String query) {
        return SO_API_URL_2_2 + "search/advanced?order=desc&sort=votes&q=" + query + "&site=stackoverflow";
    }

    private String SO_API_ANSWER(String query) {
        return SO_API_URL_2_2 + "questions/" + query + "/answers?order=desc&sort=activity&site=stackoverflow&filter=withbody";
    }

    public StackOverflowAnswer createCall(String issue) {
        StackOverflowQuestion questionObject = createQuestion(issue);
        String answerID = questionObject.getQuestionId().toString();
        StackOverflowAnswer answerObject = createAnswer(answerID);
        return answerObject;
    }

    private StackOverflowQuestion createQuestion(String issue) {
        StackOverflowQuestion questionObject;
        String redisContentQuestion = SpringRedisManager.getValue(issue);
        if (redisContentQuestion == null) {
            questionObject = callQuestionAPI(issue);
            SpringRedisManager.setValue(issue, questionObject.getQuestionId().toString());
        } else {
            questionObject = new StackOverflowQuestion();
           questionObject.setQuestionId(Integer.valueOf(redisContentQuestion));
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
        ResponseEntity<String> questionResponse = ServiceGeneralAPI.callGenericAPI(SO_API_QUESTION(issue_parsed));
        StackOverflowQuestion questionObject = ServiceGeneralAPI.mapResponseToQuestion(questionResponse.getBody());
        return questionObject;
    }

    private StackOverflowAnswer callAnswerAPI(String answerID) {
        ResponseEntity<String> answerResponse = ServiceGeneralAPI.callGenericAPI(SO_API_ANSWER(answerID));
        StackOverflowAnswer answerObject = ServiceGeneralAPI.mapResponseToAnswer(answerResponse.getBody());
        return answerObject;
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
