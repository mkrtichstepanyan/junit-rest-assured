package org.example.posts.get.negative;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetPostWithNegativeIdTests {

    @Test
    public void validateStatusCode(){
        RequestUtils.getPostWithNegativeId();
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, result);
    }

    @Test
    public void getPostWithNegativeId(){
        RequestUtils.getPostWithNegativeId();
        ResponseUtils.validateResponseByJsonSchema();
    }
}
