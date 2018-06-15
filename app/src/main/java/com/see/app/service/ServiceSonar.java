package com.see.app.service;

import com.see.app.sonar.SonarObject;
import org.springframework.http.ResponseEntity;

public class ServiceSonar {

    private static final String SONAR_API_URL = "http://localhost:9000/api/issues/search";


    public static SonarObject createCall(){
        ResponseEntity<String> answerResponse = ServiceGeneralAPI.callGenericAPI(SONAR_API_URL);
        SonarObject answerObject = ServiceGeneralAPI.mapResponseToSonar(answerResponse.getBody());
        return answerObject;
    }

}
