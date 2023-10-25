package org.example.posts.delete;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeletePostNegativeTests {

    RequestSpecification deleteSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("DELETE");
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    public void verifyDeletionOfNonExistingPost() {
        RequestUtils.delete(deleteSpec, "/posts/", 123456789);
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, result);

        RequestUtils.get(getSpec, "/posts/", 123456789);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);
    }
}
