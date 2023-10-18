package org.example.posts.get.nonexisting;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetPostByWrongPathTests {

    @Test
    public void validateStatusCode(){
        RequestUtils.getPostByWrongPath();
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404,result);
    }

    @Test
    public void getPostByWrongPath(){
        RequestUtils.getPostByWrongPath();
        ResponseUtils.validateResponseByJsonSchema();
    }
}
