package com.example;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

public class PostExm {

    @Test
    public void PostTest() {

        RestAssured.baseURI = "https://reqres.in/api";

        String body = "{\r\n" + //
                "    \"name\": \"morpheus\",\r\n" + //
                "    \"job\": \"leader\"\r\n" + //
                "}";

        Response res = given()
                .contentType(ContentType.JSON) // Specify content type
                .body(body) // Attach JSON data
                .when()
                .post("/users") // Send POST request
                .then()
                .extract().response(); // Extract response

        System.out.println(res.getBody().asPrettyString());

    
    }
}
