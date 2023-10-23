package org.example.posts.delete.negativerequests;

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
    public void verifyDeletionUnavailableId() {
        RequestUtils.delete(deleteSpec, "/posts/", 485678);
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, result);

        RequestUtils.get(getSpec, "/posts/", 485678);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);
    }
}
