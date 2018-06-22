package com.see.app.service;

import com.see.app.redisServer.SpringRedisManager;
import com.see.app.stackOverflow.StackOverflowAnswer;
import com.see.app.stackOverflow.StackOverflowQuestion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class ServiceStackOverflow {

    private static final String SO_API_URL_2_2 = "https://api.stackexchange.com/2.2/";
    private static final Logger logger = LoggerFactory.getLogger(ServiceStackOverflow.class);
    @Autowired
    public ServiceStackOverflow() {
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
            logger.info("Redis value is not present, calling StackQuestion api with: " + issue);
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
            logger.info("Redis value is not present, calling StackAnswer api with: " + answerID);
            answerObject = callAnswerAPI(answerID);
            SpringRedisManager.setValue(answerID,answerObject.getBody());
        } else {
            logger.info("Redis value is present, when the value \' " + answerID + "\' is used, return of it is" +redisContent);
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
