package com.see.app.service;

import com.see.app.sonar.SonarObject;

public class ServiceSonar {

    private static final String SONAR_API_URL = "http://localhost:9000/api/issues/search";


    public static SonarObject createCall(){
        return ServiceGeneralAPI.mapResponseToSonar(
                ServiceGeneralAPI.callGenericAPI(SONAR_API_URL).getBody());
    }

}
