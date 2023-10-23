package org.example.posts.delete.positivrequests;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeletePostPositiveTests {
    RequestSpecification deleteSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("DELETE");
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    public void verifyDeleteProcess() {
        RequestUtils.delete(deleteSpec, "/posts/", +11);
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, result);

        RequestUtils.get(getSpec, "/posts/", 11);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);
    }
}
