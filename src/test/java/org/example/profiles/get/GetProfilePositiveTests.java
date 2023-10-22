package org.example.profiles.get;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataProviders.requestSpecifications.RequestSpecificationProvider;
import org.example.pojos.profile.ProfileRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GetProfilePositiveTests {

    public String endpoint = "/profile/";
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");


    @Test
    public void validateStatusCode() {
        RequestUtils.get(getSpecs, endpoint);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    public void validateResponseByJsonSchema() {
        RequestUtils.get(getSpecs, endpoint);
        ResponseUtils.validateResponseByJsonSchema("validatorschemas/getProfileValidatorSchema.json");
    }

    @Test
    public void validateGetProfile() {
        RequestUtils.get(getSpecs, endpoint);
        List<ProfileRoot> profileList = ResponseUtils.getObjectByJsonString(List.class);
        Assertions.assertNotNull(profileList);
        Assertions.assertFalse(profileList.isEmpty());

    }
}
