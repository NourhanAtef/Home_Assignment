package com.vodafone.GameAPIs;

import com.google.gson.JsonObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateUser {
    JsonObject payload = new JsonObject();


    RequestSpecification requestSpecification ;
    ResponseSpecification responseSpec ;



    public String CreateUserAndReturnUserToken(String nickname, String email){
        payload.addProperty("nickname", nickname);
        payload.addProperty("email",email);

        requestSpecification =  new RequestSpecBuilder().
                setBody(payload).
                setContentType(ContentType.JSON).
                build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(201).build();

        String token = given().
                spec(requestSpecification).
                when().
                log().body().
                post("https://piskvorky.jobs.cz/api/v1/user").
                then().log().body().
                spec(responseSpec).
                extract().
                path("userToken").toString();
        System.out.println("usertoken: "+token);

        return token;
    }
    public void CreateUserWithExistingCredentials(String nickname, String email){
        payload.addProperty("nickname", nickname);
        payload.addProperty("email",email);

        requestSpecification =  new RequestSpecBuilder().
                setBody(payload).
                setContentType(ContentType.JSON).
                build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(400).build();

        given().
                spec(requestSpecification).
                when().
                log().body().
                post("https://piskvorky.jobs.cz/api/v1/user").
                then().log().body().
                spec(responseSpec).
                assertThat().
                body("errors.nickname",containsString(nickname)).
                assertThat().
                body("errors.email",containsString(email));

    }

}
