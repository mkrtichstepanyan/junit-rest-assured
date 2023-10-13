package org.example.api;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.response.ValidatableResponse;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ResponseUtils {

    public static ValidatableResponse getResponse() {
        return RequestUtils.getResponse();
    }

    public static int getStatusCode() {
        return getResponse().extract().statusCode();
    }


    public static void validateGetAllResponseByJsonSchema() {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(
                        ValidationConfiguration.newBuilder()
                                .setDefaultVersion(SchemaVersion.DRAFTV4).freeze())
                .freeze();
        getResponse().assertThat()
                .body(matchesJsonSchemaInClasspath("validatorschemas/getAllPostsValidatorSchema.json")
                        .using(jsonSchemaFactory));
    }
    public static void validateGetByIdResponseByJsonSchema() {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(
                        ValidationConfiguration.newBuilder()
                                .setDefaultVersion(SchemaVersion.DRAFTV4).freeze())
                .freeze();
        getResponse().assertThat()
                .body(matchesJsonSchemaInClasspath("validatorschemas/getByIdValidatorSchema.json")
                        .using(jsonSchemaFactory));
    }

    private static <T>T jsonToObject(Class<T> clazz){
        return getResponse().extract().as(clazz);
    }

}
