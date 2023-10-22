package org.example.comments.get;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecification.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GetCommentsNegativeTests {

    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    @DisplayName("Getting comment by wrong id number")
    public void validateStatusCodeByWrongId() {
        RequestUtils.get(getSpecs,"/comments/",-1000);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);
    }
    @Test
    @DisplayName("Getting comment non existing id number")
    public void validateStatusCodeByNonExistingId() {
        RequestUtils.get(getSpecs,"/comments/",1000);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);
    }

}
