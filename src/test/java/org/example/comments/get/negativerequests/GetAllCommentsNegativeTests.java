package org.example.comments.get.negativerequests;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetAllCommentsNegativeTests {
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    public void validateGetCommentsByWrongPath() {
        RequestUtils.get(getSpec, "/comment");
        int resultByStatusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, resultByStatusCode);
    }
}
