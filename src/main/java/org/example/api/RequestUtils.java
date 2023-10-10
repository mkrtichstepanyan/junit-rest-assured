package org.example.api;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
                .then()
                .log().ifError();
    }
    public static void getPostById() {
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .when()
                .get("/posts/1")
                .then()
                .log().ifError();
    }

    public static void getPostWithParameter(){
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .param("id",1)
                .when()
                .get("/posts")
                .then()
                .log().ifError();
    }
    public static void addPost() {
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"id\": 27,\n" +
                        "    \"title\": \"post 26\",\n" +
                        "    \"author\": \"type 26\"\n" +
                        "}")
                .when()
                .post("/posts")
                .then()
                .log().ifError();
    }

    public static void changePostByIdWithPut(){
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "      \"id\": 2,\n" +
                        "      \"title\": \"json-server_2\",\n" +
                        "      \"author\": \"typicode_222\"\n" +
                        "    }")
                .when()
                .put("/posts/2")
                .then()
                .log().ifError();
    }

    public static void changePostByIdWithPatch(){
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .contentType(ContentType.JSON)
                .body("{     \n" +
                        "      \"author\": \"typicode_2\"\n" +
                        "}")
                .when()
                .patch("/posts/2")
                .then()
                .log().ifError();
    }

    public static void deletePostById(){
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .when()
                .delete("/posts/2")
                .then()
                .log().ifError();
    }

}
