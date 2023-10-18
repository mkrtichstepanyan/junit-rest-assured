package org.example.posts.get.positiv;

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
}
