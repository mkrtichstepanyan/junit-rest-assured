package org.example.comments.get;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetCommentPositiveTests {
    public static final String VALIDATOR_FILE = "validatorschemas/getAllCommentsValidatorSchema.json";
    public static final String ENDPOINT = "/comments";
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");


    @Test
    public void validateGetCommentsStatusCode() {
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
