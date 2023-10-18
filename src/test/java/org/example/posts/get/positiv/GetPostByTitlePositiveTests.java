package org.example.posts.get.positiv;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetPostByTitlePositiveTests {
    @Test
    public void validateStatusCode(){
        RequestUtils.getPostByValidTitle();
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, result);
    }

    @Test
    public void getPostByTitle(){
        RequestUtils.getPostByValidTitle();
        ResponseUtils.validateResponseByJsonSchema();
    }
}
