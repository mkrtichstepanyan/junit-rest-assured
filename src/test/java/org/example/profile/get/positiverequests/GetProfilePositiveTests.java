package org.example.profile.get.positiverequests;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetProfilePositiveTests {

    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");
    private static final String ENDPOINT = "/profile";

    @Test
    public void validateGetProfileStatusCode() {
        RequestUtils.get(getSpec, ENDPOINT);
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, result);
    }

    @Test
    public void validateBodyByJsonSchema() {
        RequestUtils.get(getSpec, ENDPOINT);
        ResponseUtils.validateResponseByJsonSchema("validatorschemas/getAllProfilesValidatorSchema.json");
    }
}
