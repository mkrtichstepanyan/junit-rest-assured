package org.example.profile.post.positiverequests;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.example.models.Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CreateNewProfileTests {
    RequestSpecification postSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("POST");
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @ParameterizedTest
    @CsvFileSource(resources = "/csvfiles/profile/validProfileValues.csv")
    public void validateCreationNewProfile(String name, String surname, int age) {
        Profile expectedProfile = new Profile(name, surname, age);
        String jsonStringBody = RequestUtils.getJsonStringByObject(expectedProfile);
        RequestUtils.post(postSpec, "/profile", jsonStringBody);
        int result = ResponseUtils.getStatusCode();
        String jsonStringByObject = RequestUtils.getJsonStringByObject(Profile.class);
        Assertions.assertEquals(201, result);

        Profile actulProfile = ResponseUtils.getObjectByJsonString(Profile.class);
        RequestUtils.get(getSpec, "/profile/");
        Assertions.assertEquals(expectedProfile, actulProfile);
    }
}
