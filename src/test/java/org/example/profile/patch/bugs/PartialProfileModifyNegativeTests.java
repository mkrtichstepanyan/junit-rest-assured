package org.example.profile.patch.bugs;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.converter.JsonConverter;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PartialProfileModifyNegativeTests {
    RequestSpecification patchSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("PATCH");
    String jsonBody = JsonConverter
            .convertFileToJson("src/test/resources/jsonfiles/profilePartialModifyNegativeValues.json");
    private static final String ENDPOINT = "/profile";

    @Test
    public void validateProfilePartialModifyByInvalidValue() {
        RequestUtils.patch(patchSpec, ENDPOINT, "Karin", jsonBody);
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, result);

        String surname = ResponseUtils.getStringValueByJsonPath("surname");
        boolean contains = jsonBody.contains(surname);
        Assertions.assertTrue(contains);
    }
}
