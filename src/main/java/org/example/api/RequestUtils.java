package org.example.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public final class RequestUtils {

    public static ValidatableResponse getResponse() {
        return response;
    }

    private static ValidatableResponse response;


    public static void get(RequestSpecification spec, String endpoint) {
        response = given()
                .when()
                .spec(spec)
                .get(endpoint)
                .then();
    }

    public static void get(RequestSpecification spec, String endpoint, int id) {
        response = given()
                .when()
                .spec(spec)
                .get(endpoint + id)
                .then();
    }


    public static void post(RequestSpecification spec, String endpoint, String body) {
        response = given()
                .when()
                .spec(spec)
                .body(body)
                .post(endpoint)
                .then();
    }

    public static void put(RequestSpecification spec, String endpoint, String body) {
        response = given()
                .when()
                .spec(spec)
                .body(body)
                .put(endpoint)
                .then();
    }
    public static void put(RequestSpecification spec, String endpoint, String body, int id) {
        response = given()
                .when()
                .spec(spec)
                .body(body)
                .put(endpoint+id)
                .then();
    }

    public static void patch(RequestSpecification spec, String endpoint, String body) {
        response = given()
                .when()
                .spec(spec)
                .body(body)
                .patch(endpoint)
                .then();
    }
    public static void patch(RequestSpecification spec, String endpoint, String body, int id) {
        response = given()
                .when()
                .spec(spec)
                .body(body)
                .patch(endpoint+id)
                .then();
    }
    public static void delete(RequestSpecification spec, String endpoint, int id) {
        response = given()
                .when()
                .spec(spec)
                .delete(endpoint + id)
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
