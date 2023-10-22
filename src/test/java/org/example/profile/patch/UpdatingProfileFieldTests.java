package org.example.profile.patch;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecification.RequestSpecificationProvider;
import org.example.models.Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UpdatingProfileFieldTests {

    RequestSpecification patchSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("PATCH");
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    @DisplayName("Updating profile field and checking status code and body")
    public void validateProfileFieldUpdatingStatusCode() {

        RequestUtils.get(getSpecs, "/profile");
        Profile profile = ResponseUtils.getObjectByJsonString(Profile.class);
        profile.number = 1015;

        String jsonStringByObject = RequestUtils.getJsonStringByObject(profile);

        RequestUtils.patch(patchSpecs, "/profile", jsonStringByObject);
        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);
        RequestUtils.get(getSpecs, "/profile");

        Profile expectedProfile = ResponseUtils.getObjectByJsonString(Profile.class);

        Assertions.assertEquals(profile, expectedProfile);
    }
}
