package org.example.comments.get.positiverequests;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetCommentPositiveTests {

    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");
    private static final String ENDPOINT = "/comments";

    @Test
    public void validateGetCommentsStatusCode() {
        RequestUtils.get(getSpec, ENDPOINT);
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, result);
    }

    @Test
    public void validateBodyByJsonSchema() {
        RequestUtils.get(getSpec, ENDPOINT);
        ResponseUtils.validateResponseByJsonSchema("validatorschemas/getAllCommentsValidatorSchema.json");
    }


}
