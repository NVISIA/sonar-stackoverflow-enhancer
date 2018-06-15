package com.see.app.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.see.app.sonar.SonarObject;
import com.see.app.stackOverflow.StackOverflowAnswer;
import com.see.app.stackOverflow.StackOverflowQuestion;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class ServiceGeneralAPI {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static ResponseEntity<String> callGenericAPI(String resourceUrl) {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build());
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
        assert (response.getStatusCode() == (HttpStatus.OK));
        return response;
    }

    public static String getContent(String response) {
        try {
            JsonNode array = objectMapper.readValue(response, JsonNode.class);
            return array.get("items").get(0).toString();
        } catch (Exception exception) {
            throw new AssertionError("Can't read the values from the json");
        }
    }
    public static StackOverflowQuestion mapResponseToQuestion(String response) {
        try {
            return objectMapper.readValue(getContent(response), StackOverflowQuestion.class);
        } catch (Exception error) {
            throw new AssertionError("Can't map json to a java object");
        }
    }

    public static StackOverflowAnswer mapResponseToAnswer(String response) {
        try {
            return objectMapper.readValue(getContent(response), StackOverflowAnswer.class);
        } catch (Exception error) {
            throw new AssertionError("Can't map json to a java object");
        }
    }

    public static String getSonarContent(String response) {
        try {
            JsonNode array = objectMapper.readValue(response, JsonNode.class);
            return array.toString();
        } catch (Exception exception) {
            throw new AssertionError("Can't read the values from the json");
        }
    }


    public static SonarObject mapResponseToSonar(String response)
    {
        try {
            return objectMapper.readValue(getSonarContent(response), SonarObject.class);
        } catch (Exception error) {
            throw new AssertionError("Can't map json to a java object");
        }
    }


}
