package org.example.profile.put.bugs;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.example.models.Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ModifyTotalProfileNegativeTests {

    RequestSpecification putSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("PUT");
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @ParameterizedTest
    @CsvSource(
            {
                    "@Ani, #karapet%, -45"
            }
    )
    public void validatePostModificationByWrongValues(String name, String surname, int age) {
        Profile expectedProfile = new Profile(name, surname, age);
        String jsonBody = RequestUtils.getJsonStringByObject(expectedProfile);
        RequestUtils.put(putSpec, "/profile", name, jsonBody);
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, result);

        RequestUtils.get(getSpec, "/profile");
        Profile actualProfile = ResponseUtils.getObjectByJsonString(Profile.class);
        Assertions.assertEquals(expectedProfile, actualProfile);
    }
}
