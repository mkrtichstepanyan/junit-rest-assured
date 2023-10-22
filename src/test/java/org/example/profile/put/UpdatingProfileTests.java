package org.example.profile.put;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecification.RequestSpecificationProvider;
import org.example.models.Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UpdatingProfileTests {

    RequestSpecification putSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("PUT");
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    @DisplayName("Updating profile and checking status code ")
    public void validateProfileUpdatingStatusCode() {
       Profile profile = new Profile("New Profile by put method", 5050);
        String jsonStringByObject = RequestUtils.getJsonStringByObject(profile);

        RequestUtils.put(putSpecs, "/profile", jsonStringByObject);
        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);
    }

    @Test
    @DisplayName("Updating profile, checking status code and body")
    public void validateCommentUpdating() {
        Profile profile = new Profile("New Profile by put method-12", 1212);

        String jsonStringByObject = RequestUtils.getJsonStringByObject(profile);

        RequestUtils.put(putSpecs, "/profile", jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);

        RequestUtils.get(getSpecs, "/profile");

        Profile expectedProfile = ResponseUtils.getObjectByJsonString(Profile.class);

        Assertions.assertEquals(profile, expectedProfile);
    }

}
