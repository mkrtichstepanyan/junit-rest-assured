package org.example.profile.post.bugs;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.example.models.Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CreateNewProfileByNegativeTests {

    RequestSpecification postSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("POST");
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @ParameterizedTest
    @CsvFileSource(resources = "/csvfiles/profile/invalidProfileValues.csv")
    public void validateCreationProfileByInvalidValues(String name, String surname, int age) {
        Profile expectedProfile = new Profile(name, surname, age);
        String jsonStringBody = RequestUtils.getJsonStringByObject(expectedProfile);
        RequestUtils.post(postSpec, "/profile", jsonStringBody);
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, result);

        Profile actualProfile = ResponseUtils.getObjectByJsonString(Profile.class);
        RequestUtils.get(getSpec, "/profile/");
        Assertions.assertEquals(expectedProfile, actualProfile);
    }

    @Test
    public void validateCreationProfileWithoutBody() {
        Profile expectedProfile = new Profile();
        String jsonStringBody = RequestUtils.getJsonStringByObject(expectedProfile);
        RequestUtils.post(postSpec, "/profile", jsonStringBody);
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(101, result);

        Profile actualProfile = ResponseUtils.getObjectByJsonString(Profile.class);
        RequestUtils.get(getSpec, "/profile/");
        Assertions.assertEquals(expectedProfile, actualProfile);
    }

}
