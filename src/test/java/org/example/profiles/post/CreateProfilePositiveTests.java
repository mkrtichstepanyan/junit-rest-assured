package org.example.profiles.post;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataProviders.requestSpecifications.RequestSpecificationProvider;
import org.example.models.Post;
import org.example.models.Profiles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CreateProfilePositiveTests {
    public String endpoint = "/profile";
    RequestSpecification postSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("POST");


    @Test
    public void validateProfileCreation() {

        Profiles profile = new Profiles("Author3");

        String jsonStringByObject = RequestUtils.getJsonStringByObject(profile);

        RequestUtils.post(postSpecs, endpoint, jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(201, statusCode);
    }
}
