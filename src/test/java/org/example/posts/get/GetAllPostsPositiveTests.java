package org.example.posts.get;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecification.RequestSpecificationProvider;
import org.example.pojos.posts.PostsRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


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

        RequestUtils.get(getSpecs, "/posts/1");

        PostsRoot objectByJsonString = ResponseUtils.getObjectByJsonString(PostsRoot.class);

        ResponseUtils.getStringValueByJsonPath("id");

    }
}


