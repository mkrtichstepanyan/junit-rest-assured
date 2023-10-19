package org.example.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

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

    public static void get(RequestSpecification spec, String endpoint, Map<String, Object> params) {
        response = given()
                .when()
                .queryParams(params)
                .spec(spec)
                .get(endpoint)
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
