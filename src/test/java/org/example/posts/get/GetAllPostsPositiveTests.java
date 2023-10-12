package org.example.posts.get;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetAllPostsPositiveTests {

    @Test
    public void validateStatusCodeForWrongID() {
        RequestUtils.getPostByID(-1);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);
    }

    @Test
    public void getPostByID() {
        RequestUtils.getPostByID(-1);
        int responseBody = ResponseUtils.getResponseBody().asString().length();
        Assertions.assertEquals(2, responseBody);
    }
}
