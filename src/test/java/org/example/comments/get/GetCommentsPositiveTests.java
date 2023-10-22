package org.example.comments.get;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecification.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetCommentsPositiveTests {
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    public void validateStatusCode() {
        RequestUtils.get(getSpecs,"/comments");
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    public void validateStatusCodeById() {
        RequestUtils.get(getSpecs,"/comments/",2);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    public void validateResponseByJsonSchema() {
        RequestUtils.get(getSpecs,"/comments");
        ResponseUtils.validateResponseByJsonSchema("validatorschemas/getAllCommentsValidatorSchemas.json");
    }

}
