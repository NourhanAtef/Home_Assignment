package com.vodafone.GameAPIs;


import com.google.gson.JsonObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
//import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreateMove {
    JsonObject payload = new JsonObject();
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201).build();

    public Response MakeMoveAndReturnGameResponse(String userToken, String gameToken, int positionX, int positionY){

        payload.addProperty("userToken", userToken);
        payload.addProperty("gameToken",gameToken);
        payload.addProperty("positionX",positionX);
        payload.addProperty("positionY", positionY);
        System.out.println("move params: "+ payload.toString());
        requestSpecification =  new RequestSpecBuilder().
                setBody(payload).
                setContentType(ContentType.JSON).
                build();
        Response response =
                given().
                        when().
                        spec(requestSpecification).
                        post("https://piskvorky.jobs.cz/api/v1/play").
                        then().
                        log().body().
                        spec(responseSpec).
                        extract().
                        response();
        System.out.println(response);
        return response;
    }
}
