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

    public static void getInvalidPostById(){
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .when()
                .get("/posts/kar")
                .then();
    }

    public static void getNonExistingPostById(){
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .when()
                .get("posts/4568899")
                .then();
    }

    public static void getTwoPostsByValidIds(){
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .param("id",1,2)
                .when()
                .get("/posts")
                .then();
    }

    public static void getTwoPostsByValidAndInvalidIds(){
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .param("id", 1, 789564)
                .when()
                .get("/posts")
                .then();
    }

    public static void getPostByValidTitle(){
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .param("title","JS")
                .when()
                .get("/posts")
                .then();
    }

    public static void getPostByInvalidTitle(){
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .param("title", 4578)
                .when()
                .get("/posts")
                .then();
    }

    public static void getPostByNonExistingTitle(){
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .param("title", "juky")
                .when()
                .get("/posts")
                .then();
    }

    public static void getPostWithOfVaredParameters(){
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .param("title", 4578, "author", 478956)
                .when()
                .get("/posts")
                .then();
    }

    public static void getPostWithNegativeId(){
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .when()
                .get("/posts/-1")
                .then();
    }

    public static void getPostByWrongPath(){
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .when()
                .get("/post")
                .then();
    }


//    public static void post() {
//        response =
//    }

}
