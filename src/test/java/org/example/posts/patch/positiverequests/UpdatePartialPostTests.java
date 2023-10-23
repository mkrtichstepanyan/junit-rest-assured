package org.example.posts.patch.positiverequests;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.converter.JsonConverter;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UpdatePartialPostTests {

    RequestSpecification patchSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("PATCH");
    String jsonStringBody = JsonConverter
            .convertFileToJson("src/test/resources/jsonfiles/postPartialUpdateValues.json");


    @Test
    public void validatePartialModification() {
        RequestUtils.patch(patchSpec, "/posts/", 10, jsonStringBody);
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, result);

        String author = ResponseUtils.getStringValueByJsonPath("author");
        boolean contains = jsonStringBody.contains(author);
        Assertions.assertTrue(contains);
    }
}
