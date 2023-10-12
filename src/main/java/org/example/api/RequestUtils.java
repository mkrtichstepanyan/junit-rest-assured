package org.example.api;


import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public final class RequestUtils {

    private static ValidatableResponse response;


    public static ValidatableResponse getResponse() {
        return response;
    }


    public static void getAllPosts() {
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .when()
                .get("/posts")
                .then();
    }

    public static void getPostByID(int postID) {
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .when()
                .get("/posts/" + postID)
                .then();
    }

    public static void getPostByParam(String key, String value) {
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .queryParam(key, value)
                .when()
                .get("/posts")
                .then();
    }

//    public static void post() {
//        response =
//    }

}
