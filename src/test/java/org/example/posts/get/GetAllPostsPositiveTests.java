package org.example.posts.get;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.pojos.posts.PostsRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetAllPostsPositiveTests {

    @Test
    public void validateStatusCode() {
        RequestUtils.get("/posts");
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    public void validateResponseByJsonSchema() {
        RequestUtils.get("/posts");
        ResponseUtils.validateResponseByJsonSchema("validatorschemas/getAllPostsValidatorSchema.json");
    }


    @Test
    public void validateGetPostById() {
        RequestUtils.get("/posts/1");

        PostsRoot objectByJsonString = ResponseUtils.getObjectByJsonString(PostsRoot.class);

        ResponseUtils.getStringValueByJsonPath("id");
        ResponseUtils.getStringValueByJsonPath("user.name");
    }
}
