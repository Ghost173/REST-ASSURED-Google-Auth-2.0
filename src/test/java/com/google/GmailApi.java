package com.google;

import io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeClass;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GmailApi {
    Properties prop = new Properties();

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String user_id;


    @BeforeClass
    public void beforeClass() {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            prop.load(fis);
            String access_token = prop.getProperty("access_token");
            user_id = prop.getProperty("user_id");

            requestSpecification = new RequestSpecBuilder()
                    .setBaseUri("https://oauth2.googleapis.com")
                    .addHeader("Authorization", "Bearer "+access_token)
                    .setContentType(ContentType.JSON)
                    .log(LogDetail.ALL)
                    .build();

            responseSpecification = new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectContentType(ContentType.JSON)
                    .log(LogDetail.ALL)
                    .build();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserProfile() {
        given()
                .basePath("/gmail/v1")
                .pathParam("userid", user_id)
                .when()
                .get("/users/{userid}/profile")
                .then();
    }




}
