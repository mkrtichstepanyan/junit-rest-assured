package org.example.posts.get;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetPostsPositiveTests {

    @Test
    public void validateStatusCode() {
        RequestUtils.getAllPosts();
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
        System.out.println( ResponseUtils.getResponse().extract().asPrettyString());
    }
    @Test
    public void getPostById() {
        RequestUtils.getPostById();
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
        System.out.println( ResponseUtils.getResponse().extract().asPrettyString());
    }

    @Test
    public void getPostWithParameter(){
        RequestUtils.getPostWithParameter();
        Assertions.assertEquals(200,ResponseUtils.getStatusCode());
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());
        validateResponseByJsonSchema();
    }

    @Test
    public void validateResponseByJsonSchema() {
        RequestUtils.getAllPosts();
        ResponseUtils.validateResponseByJsonSchema();
    }
}
