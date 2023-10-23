package org.example.profile.get.bugs;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetProfileNegativeTests {
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");
    private static final String ENDPOINT = "/profile";

    @Test
    public void validateGetProfileByNonExistingValue() {
        RequestUtils.get(getSpec, ENDPOINT, "name", "Araks");
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, result);
    }

    @Test
    public void validateGetProfileByNonExistingKey() {
        RequestUtils.get(getSpec, ENDPOINT, "car", "Abrahamian");
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, result);
    }

}
