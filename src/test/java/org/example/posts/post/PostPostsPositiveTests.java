package org.example.posts.post;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PostPostsPositiveTests {
    @Test
    public void addPost() {
        RequestUtils.addPost();
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(201, statusCode);
        System.out.println( ResponseUtils.getResponse().extract().asPrettyString());
    }
}
