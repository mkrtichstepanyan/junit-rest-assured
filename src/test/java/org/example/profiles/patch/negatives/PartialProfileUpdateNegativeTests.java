package org.example.profiles.patch.negatives;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.JsonConverter;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PartialProfileUpdateNegativeTests {


    public static final String ENDPOINT = "/profile";
    public static final String INVALID_VALUE_SOURCE_FILE = "src/test/resources/jsonfiles/profile_partial_update_invalid_values.json";
    String jsonBody = JsonConverter.convertFileToJson(INVALID_VALUE_SOURCE_FILE);
    RequestSpecification patchSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("PATCH");

    @Test
    public void validateProfilePartialUpdateByInvalidValues() {
        RequestUtils.patch(patchSpec, ENDPOINT, "Kovalenko", jsonBody);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);

        String name = ResponseUtils.getStringValueByJsonPath("name");
        boolean contains = jsonBody.contains(name);
        Assertions.assertTrue(contains);
    }
}
