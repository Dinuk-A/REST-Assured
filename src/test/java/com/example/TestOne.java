package com.example;

import java.util.List;

import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestOne {

    // to run the tests ✅
    // mvn test

    // to run only this specific test class ✅
    // mvn test -Dtest=TestOne

    // to run this method only ✅
    // mvn test -Dtest=TestOne#get_01

    // method 1 ✅
    @Test
    public void test_01() {

        //set a base URL and then use relative paths for requests ✅
        RestAssured.baseURI = "https://reqres.in/api";
        Response response = RestAssured.get("/users?page=2");

            //print what came with response body ✅
        System.out.println("Response Body: ");
        System.out.println(response.getBody().asPrettyString());

        // TestNG only prints assertion failures by default ✅
        // assertEquals === to compare 2 values are EXACTLY MATCHES(EQUAL) OR NOT
        // assertTrue === does not compare two specific values,
        // it verifies if a CONDITION evaluates to true or false
        //Assert.assertTrue(response.getTime() < 2000, "Response time is too slow!");

        // validate the status code ✅
        Assert.assertEquals(response.getStatusCode(), 200);

        // validate the header ✅
        Assert.assertTrue(response.getContentType().contains("application/json"));

        //more asste TRUEs ✅
        List<Integer> userIds = response.jsonPath().getList("data.id");
        Assert.assertTrue(userIds.contains(9), "ID 9 not found in the response!");

        String supportText = response.jsonPath().getString("support.text");
        Assert.assertTrue(supportText.contains("social media"), "'social media' keyword not found!");

        // explicitly print the assertion results if you want to see them(only when
        // fails) ✅
        Assert.assertEquals(response.getStatusCode(), 200, "Status Code Mismatch!");

        // get the user count from 'data' array ✅
        int usersCount = response.jsonPath().getList("data").size();
        System.out.println(" Users Count = " + usersCount);
        Assert.assertEquals(usersCount, 6, "User count mismatch!");

        // extract a data ✅
        String firstUserEmail = response.jsonPath().getString("data[0].email");
        System.out.println("First User Email = " + firstUserEmail);
        Assert.assertEquals(firstUserEmail, "michael.lawson@reqres.in", "First user email mismatch!");

    }

    // method 2 ✅
    @Test(enabled = false)
    public void test_02() {
        Response result = RestAssured.get("https://reqres.in/api/users?page=2");

        Integer statusCode = result.getStatusCode();

        System.out.println(result.getBody().asPrettyString());
        System.out.println("Time: " + result.getTime());
        System.out.println("Status Code: " + statusCode);
        System.out.println("header: " + result.getHeader("content-type"));

        // just checking these are match or not, not printing any results if matched,
        // just print and show an failure if dont ✅
        Assert.assertEquals(statusCode, 200);

    }

    // put this if we wanted to skip a test method ✅
    @Test(enabled = false)
    public void test_03() {

    }

}
