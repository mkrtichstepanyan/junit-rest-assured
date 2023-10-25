package org.example.profiles.get.positives;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetProfileTests {
    public static final String VALIDATOR_FILE = "validatorschemas/getAllProfilesValidatorSchema.json";
    public static final String ENDPOINT = "/profile";
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");


    @Test
    public void validateGetProfileStatusCode() {
        RequestUtils.get(getSpec, ENDPOINT);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    public void validateBodyByJsonSchema() {
        RequestUtils.get(getSpec, ENDPOINT);
        ResponseUtils.validateResponseByJsonSchema(VALIDATOR_FILE);
    }

}
