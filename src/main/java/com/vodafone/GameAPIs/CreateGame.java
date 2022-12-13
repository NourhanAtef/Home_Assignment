package com.vodafone.GameAPIs;

import com.google.gson.JsonObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
//import org.json.JSONObject;


import static io.restassured.RestAssured.*;

public class CreateGame {


    RequestSpecification requestSpecification;

    ResponseSpecification responseSpecification = new ResponseSpecBuilder().
            expectStatusCode(201).
            expectContentType(ContentType.JSON).
            build();



    public String JoinGameAndReturnGameToken(String userToken){

        JsonObject payload = new JsonObject();
        payload.addProperty("userToken",userToken);
         requestSpecification =  new RequestSpecBuilder().
                setBody(payload.toString()).
                setContentType("application/json").
                build();

        System.out.println("game request params: "+ payload);
        System.out.println("game request params length: "+ payload.entrySet().size());
        String gameToken =
                given().
                                when().
                        spec(requestSpecification).
                        post("https://piskvorky.jobs.cz/api/v1/connect").
                        then().
                        spec(responseSpecification).
                        log().body().
                        extract().
                        path("gameToken").toString();
        System.out.println("gameToken: "+gameToken);
        return gameToken;
    }



}
