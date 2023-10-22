package org.example.posts.get;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecification.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GetAllPostsNegativeTests {

    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");
    @Test
    @DisplayName("Get post with non existing id number")
    public void validateStatusCodeByNonExistingId() {

        RequestUtils.get(getSpecs, "/posts", 100);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);
    }
    @Test
    @DisplayName("Get post with wrong id number")
    public void validateStatusCodeByWrongId() {

        RequestUtils.get(getSpecs, "/posts", -6);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);
    }
}
