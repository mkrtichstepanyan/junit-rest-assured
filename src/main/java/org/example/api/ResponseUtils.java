package org.example.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.response.ValidatableResponse;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ResponseUtils {

    public static ValidatableResponse getResponse() {
        return RequestUtils.getResponse();
    }

    public static int getStatusCode() {
        return getResponse().extract().statusCode();
    }


    public static void validateResponseByJsonSchema(String path) {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(
                        ValidationConfiguration.newBuilder()
                                .setDefaultVersion(SchemaVersion.DRAFTV4).freeze())
                .freeze();
        getResponse().assertThat()
                .body(matchesJsonSchemaInClasspath(path)
                        .using(jsonSchemaFactory));
    }


    public static <T> T getObjectByJsonString(Class<T> type) {
        T t = getResponse()
                .extract()
                .as(type);
        return t;
    }

    public static String getStringValueByJsonPath(String jsonpath) {
        return getResponse().extract().jsonPath().getString(jsonpath);
    }
}
