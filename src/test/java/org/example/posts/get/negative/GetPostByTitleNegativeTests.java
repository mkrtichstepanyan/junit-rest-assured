package org.example.posts.get.negative;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetPostByTitleNegativeTests {

    @Test
    public void validateStatusCode(){
        RequestUtils.getPostByInvalidTitle();
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, result);
    }

    @Test
    public void getPostByTitle(){
        RequestUtils.getPostByInvalidTitle();
        ResponseUtils.validateResponseByJsonSchema();
    }
}
