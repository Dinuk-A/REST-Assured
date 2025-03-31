package com.example;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;

// to run the tests ✅
// mvn test

// to run only this specific test class ✅
// mvn test -Dtest=TestOne

// to run this method only ✅
// mvn test -Dtest=TestOne#get_01

public class ResponseExm {

    @Test
    public void basics() {

        RestAssured.baseURI = "https://reqres.in/api";
        Response response = RestAssured.get("/users?page=2");

        // get info from the Response object ✅✅✅

        // full body(as a formatted string)✅
        System.out.println("Response Body: ");
        System.out.println(response.getBody().asPrettyString());

        // status code ✅
        int statusCode = response.statusCode();
        System.out.println("Status Code: " + statusCode);

        // full status line ✅
        String statusLine = response.getStatusLine();
        System.out.println("Full Status Line: " + statusLine);

        // response time ✅
        long resTime = response.getTime();
        System.out.println("Response Time: " + resTime);

        // full header ✅
        Headers headers = response.getHeaders();
        System.out.println("Full Response Header: " + headers);

        // a specific header ✅
        System.out.println("Specific Header: " + response.getHeader("Content-Type"));

        // list all cookies with names and values ✅        
        System.out.println("Get All Cookies: ");
        Map<String, String> cookies = response.getCookies();
        System.out.println(cookies);

        // extract an entire array ✅
        List<Map<String, Object>> dataArray = response.jsonPath().getList("data");
        System.out.println("Entire Array: ");
        System.out.println(dataArray);
        // map is for inside a single object (key value pairs)
        // list is for the all sets of json objects inside the data array

        //loop through the array ✅
        for (Map<String,Object> user : dataArray){
            System.out.println("user ID: " + user.get("id"));
        }

        // extract a value ✅
        String email = response.jsonPath().getString("data[0].email");
        System.out.println("Email: " + email);

    }
}
