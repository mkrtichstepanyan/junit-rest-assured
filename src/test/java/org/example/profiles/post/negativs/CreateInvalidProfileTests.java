package org.example.profiles.post.negativs;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.example.models.Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CreateInvalidProfileTests {
    RequestSpecification postSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("POST");
    public static final String INVALID_PROFILE_VALUES_FILE = "/csvfiles/invalid_profile_values.csv";
    public static final String ENDPOINT = "/profile";


    @ParameterizedTest
    @CsvFileSource(resources = INVALID_PROFILE_VALUES_FILE)
    public void validateProfileCreationByValidValues(String name, String surname, int age) {
        Profile expectedProfile = new Profile(name, surname, age);
        String jsonBody = RequestUtils.getJsonStringByObject(expectedProfile);
        RequestUtils.post(postSpec, ENDPOINT, jsonBody);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);

        Profile actualProfile = ResponseUtils.getObjectByJsonString(Profile.class);
        Assertions.assertEquals(expectedProfile, actualProfile);
    }

    @Test
    public void validateCreationProfileWithEmptyBody() {
        Profile expectedProfile = new Profile();
        String jsonBody = RequestUtils.getJsonStringByObject(expectedProfile);
        RequestUtils.post(postSpec, ENDPOINT, jsonBody);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(100, statusCode);

        Profile actualProfile = ResponseUtils.getObjectByJsonString(Profile.class);
        Assertions.assertNotEquals(expectedProfile, actualProfile);
    }

}
