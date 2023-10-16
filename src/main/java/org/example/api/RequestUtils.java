package org.example.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public final class RequestUtils {
    private static ValidatableResponse response;

    public static ValidatableResponse getResponse() {
        return response;
    }


    static {
        RestAssured.baseURI = "http://localhost:3000";
    }

    public static void get(String endpoint) {
        response = given()
                .when()
                .get(endpoint)
                .then();
    }

    public static void get(String endpoint, int id) {
        response = given()
                .when()
                .get(endpoint + "/" + id)
                .then();
    }


    public static void post(String endpoint, String body) {
        response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(body)
                .post(endpoint)
                .then();
    }

    public static void put(String endpoint, String body) {
        response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(body)
                .put(endpoint)
                .then();
    }


    public static void patch(String endpoint, String body) {
        response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(body)
                .patch(endpoint)
                .then();
    }


    public static void delete(String endpoint/*, int id*/) {
        response = given()
                .when()
                .delete(endpoint /*+ "/" + id*/)
                .then();
    }




    public static String getJsonStringByObject(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString;
        try {
            jsonString = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }
}
