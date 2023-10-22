package org.example.profiles.get;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataProviders.requestSpecifications.RequestSpecificationProvider;
import org.example.pojos.comments.CommentsRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetProfileNegativeTests {

    public String endpoint = "/profile";
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");


    @Test
    public void validateStatusCodeForWrongPath() {
        RequestUtils.get(getSpecs, endpoint, 1);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);
    }
}
