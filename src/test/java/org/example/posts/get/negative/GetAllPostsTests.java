package org.example.posts.get.negative;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetAllPostsTests {
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    public void validateGetByWrongPath() {
        RequestUtils.get(getSpec, "/post");
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);
    }
}
