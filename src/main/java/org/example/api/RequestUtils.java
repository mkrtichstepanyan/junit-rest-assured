package org.example.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.models.Comment;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public final class RequestUtils {

    public static ValidatableResponse getResponse() {
        return response;
    }

    private static ValidatableResponse response;

    static {
        RestAssured.baseURI = "http://localhost:3000";
    }

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
                .queryParams(params)
                .when()
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

    public static void put(RequestSpecification spec, String endpoint, String body) {
        response = given()
                .contentType(ContentType.JSON)
                .when()
                .spec(spec)
                .body(body)
                .put(endpoint)
                .then();
    }

    public static void patch(RequestSpecification spec, String endpoint, String patchData) {
        response = given()
                .contentType(ContentType.JSON)
                .when()
                .spec(spec)
                .body(patchData)
                .patch(endpoint)
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


    public static List<HashMap<String, Object>> readJsonFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class));
    }

}
