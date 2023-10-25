package org.example.posts.patch;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.JsonConverter;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ModifyPartialPostTests {

    RequestSpecification patchSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("PATCH");
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");
    public static final String ENDPOINT = "/posts";
    public static final String VALID_IDS_FILE = "/csvfiles/valid_ids.csv";
    String jsonBody = JsonConverter.convertFileToJson("src/test/resources/jsonfiles/post_partial_update_values.json");

    @ParameterizedTest
    @CsvFileSource(resources = VALID_IDS_FILE)
    public void validatePartialPostModification(int id) {
        RequestUtils.patch(patchSpec, ENDPOINT, id, jsonBody);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);

        String title = ResponseUtils.getStringValueByJsonPath("title");
        boolean contains = jsonBody.contains(title);
        Assertions.assertTrue(contains);
    }
}
