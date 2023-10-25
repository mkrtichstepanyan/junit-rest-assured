package org.example.posts.get.positive;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.example.pojos.posts.PostsRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;

public class GetAllPostsTests {
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");
    public static final String ENDPOINT = "/posts";
    public static final String VALIDATOR_FILE = "validatorschemas/getAllPostsValidatorSchema.json";

    @Test
    public void validateStatusCode() {
        RequestUtils.get(getSpecs, ENDPOINT);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    public void validateResponseByJsonSchema() {
        RequestUtils.get(getSpecs, ENDPOINT);
        ResponseUtils.validateResponseByJsonSchema(VALIDATOR_FILE);
    }


    @Test
    public void validateGetPostByParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("title", "Title11");
        params.put("Poxos", "Poxos");

        RequestUtils.get(getSpecs, "/posts", params);

        PostsRoot objectByJsonString = ResponseUtils.getObjectByJsonString(PostsRoot.class);

        ResponseUtils.getStringValueByJsonPath("id");
        ResponseUtils.getStringValueByJsonPath("user.name");
    }
}
