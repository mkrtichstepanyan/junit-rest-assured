package org.example.comments.delete;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataProviders.requestSpecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteCommentPositiveTests {

    public String endpoint = "/comments/";
    RequestSpecification deleteSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("DELETE");

    @Test
    public void validateDeleteComment() {
        int id = 4;
        RequestUtils.delete(deleteSpecs, endpoint, id);

        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
    }
}
