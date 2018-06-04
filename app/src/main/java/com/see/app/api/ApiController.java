package com.see.app.api;

// import requirements for spring controller
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
// import requirements for online API request
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Controller
public class ApiController {

    // make a single API call using the RestTemplate
    @RequestMapping(value = "/call/{issue}", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<String> callStack(@PathVariable("issue") String issue)
    {

        System.out.println("The issue is: " + issue);
        //TODO: Check that there is no api object with the same issue
        //TODO: If such object exists, reutrn it's result instead of calling API

         // create an ApiObject based on issue
        ApiObject issueObject = new ApiObject();
        //create API call to the website
        ResponseEntity<String> response = callSOApi(issueObject);

        // now we save the issue to the ApiObject as a string
        //issueObject.setResult(response.toString());

        // TODO: Store the issue object to be compared later

        return response;
    }

    //Call stackoverlfow api
    private ResponseEntity<String> callSOApi(ApiObject issueObject)
    {
        String resourceUrl = "https://jsonplaceholder.typicode.com/posts";
        // SO Api Call
        //String resourceUrl = "https://api.stackexchange.com/2.2/search?order=desc&sort=votes&tagged="+ issueObject.getIssue() +"&site=stackoverflow";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl + "/1", String.class);
        assert(response.getStatusCode() == (HttpStatus.OK));
        // TODO: any filtering on the object, such as taking out not needed fields,values, etc.
        System.out.println();
        return response;
    }

    private ApiObject cleanResult( ResponseEntity<String> response)
    {
        //TODO: format the json into a nice API object
        return new ApiObject();
    }

}