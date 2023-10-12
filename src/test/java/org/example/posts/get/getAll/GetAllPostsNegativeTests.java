package org.example.posts.get.getAll;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetAllPostsNegativeTests {
    @Test
    public void validateStatusCode() {
        RequestUtils.getAllPosts();
        int statusCode = ResponseUtils.getStatusCode();
        System.out.println( ResponseUtils.getResponse().extract().asPrettyString());
        Assertions.assertEquals(404, statusCode);
    }

}
