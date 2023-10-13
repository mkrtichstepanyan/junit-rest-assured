package org.example.posts.get;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetAllPostsPositiveTests {
    @Test
    public void validateStatusCode() {
        RequestUtils.getAllPosts();
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    public void validateResponseByJsonSchema() {
        RequestUtils.getAllPosts();
        ResponseUtils.validateResponseByJsonSchema();
    }

    @Test
    public void getAllPosts() {
        RequestUtils.getAllPosts();
        String responseBody = ResponseUtils.getResponseBody().asString();
        Assertions.assertTrue(responseBody.length() > 2);
    }

    @Test
    public void getPostByID() {
        RequestUtils.getPostByID(-1);
        int actualPostID = Integer.parseInt(ResponseUtils.getResponseBody().jsonPath().getString("id"));
        Assertions.assertEquals(2, actualPostID);
    }

    @Test
    public void getAllPostsByParam() {
        String givenKey = "author";
        String givenValue = "typicode";
        RequestUtils.getPostByParam(givenKey, givenValue);
        String paramValue = ResponseUtils.getResponseBody().jsonPath().getString(givenKey);
        Assertions.assertEquals(givenValue, paramValue);
    }

}
