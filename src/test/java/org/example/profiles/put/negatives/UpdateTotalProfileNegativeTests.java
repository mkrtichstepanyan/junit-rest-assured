package org.example.profiles.put.negatives;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.example.models.Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class UpdateTotalProfileNegativeTests {
    RequestSpecification putSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("PUT");
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");
    public static final String ENDPOINT = "/profile";
    public static final String INVALID_PROFILE_FILE = "/csvfiles/invalid_profile_values.csv";


    @ParameterizedTest
    @CsvFileSource(resources = INVALID_PROFILE_FILE)
    public void validatePostModificationByInvalidValues(String name, String surname, int age) {
        Profile expectedProfile = new Profile(name, surname, age);
        String jsonBody = RequestUtils.getJsonStringByObject(expectedProfile);
        RequestUtils.put(putSpec, ENDPOINT, name, jsonBody);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);

        RequestUtils.get(getSpec, ENDPOINT);
        Profile actualProfile = ResponseUtils.getObjectByJsonString(Profile.class);
        Assertions.assertEquals(expectedProfile, actualProfile);
    }

}
