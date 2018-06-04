package com.see.app.api;

// import requirements for spring controller
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

    private static final String API_URL_2_2 = "https://api.stackexchange.com/2.2/";

    @GetMapping("/call-api/{issue}")
    @ResponseBody
    public ApiObject getObject(@PathVariable(value="issue") String issue) {
        return createCall(issue);
    }

    // make a single API call using the RestTemplate
    private ApiObject createCall(String issue){

        //TODO: Check that there is no api object with the same issue
        //TODO: If such object exists, reutrn it's result instead of calling API

        String query = null;
        try {
            query = URLEncoder.encode(issue,"UTF-8");
        } catch (UnsupportedEncodingException ignored) {
            // usually ignored because UTF-8 is always supported, but just in case
            throw new AssertionError("UTF-8 is unknown");
        }
        // create an ApiObject based on issue
        ApiObject issueObject = new ApiObject(query);
        //create API call to the website
        ResponseEntity<String> response = callSOApi(issueObject);

        // now we save the issue to the ApiObject as a string
        issueObject.setResult(response.toString());

        // TODO: Store the issue object to be compared later

        return issueObject;
    }

    private ResponseEntity<String> callSOApi(ApiObject issueObject)
    {
        String resourceUrl = API_URL_2_2 + "search?order=desc&sort=votes&tagged="+issueObject.getIssue()+"&site=stackoverflow";
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build());
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
     ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
        assert(response.getStatusCode() == (HttpStatus.OK));
        // TODO: any filtering on the object, such as taking out not needed fields,values, etc.
        return response;
    }

}