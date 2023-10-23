package org.example.posts.get.positiverequests;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.example.pojos.posts.PostsRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class GetAllPostsPositiveTests {

    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    public void validateStatusCode() {
        RequestUtils.get(getSpecs, "/posts");
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    public void validateResponseByJsonSchema() {

        RequestUtils.get(getSpecs, "/posts");
        ResponseUtils.validateResponseByJsonSchema("validatorschemas/getAllPostsValidatorSchema.json");
    }

    @Test
    public void validateGetPostById() {
        RequestUtils.get(getSpecs, "/posts/", 1);

        PostsRoot objectByJsonString = ResponseUtils.getObjectByJsonString(PostsRoot.class);

        ResponseUtils.getStringValueByJsonPath("id");
        ResponseUtils.getStringValueByJsonPath("user.name");
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
