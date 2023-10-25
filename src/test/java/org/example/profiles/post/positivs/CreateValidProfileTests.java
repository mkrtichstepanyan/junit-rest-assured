package org.example.profiles.post.positivs;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.example.models.Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CreateValidProfileTests {
    RequestSpecification postSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("POST");
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("POST");
    public static final String VALID_PROFILE_VALUES_FILE = "/csvfiles/valid_profile_values.csv";
    public static final String ENDPOINT = "/profile";


    @ParameterizedTest
    @CsvFileSource(resources = VALID_PROFILE_VALUES_FILE)
    public void validateProfileCreationByValidValues(String name, String surname, int age) {
        Profile expectedProfile = new Profile(name, surname, age);
        String jsonBody = RequestUtils.getJsonStringByObject(expectedProfile);
        RequestUtils.post(postSpec, ENDPOINT, jsonBody);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(201, statusCode);

        Profile actualProfile = ResponseUtils.getObjectByJsonString(Profile.class);
        RequestUtils.get(getSpec, "/profile/");
        Assertions.assertEquals(expectedProfile, actualProfile);
    }

}
