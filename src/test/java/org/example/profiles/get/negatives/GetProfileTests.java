package org.example.profiles.get.negatives;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetProfileTests {
    public static final String ENDPOINT = "/profile";
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    public void validateGetProfileByNonExistingValue() {
        RequestUtils.get(getSpec, ENDPOINT, "name", "Non-existing Name");
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);
    }

    @Test
    public void validateGetProfileByNonExistingKey() {
        RequestUtils.get(getSpec, ENDPOINT, "Non Existing Key", "Ani");
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);
    }


}
