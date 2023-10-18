package org.example.posts.get.negative;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetPostByIdNegativeTests {

    @Test
    public void validateStatusCodeInvalidPost(){
        RequestUtils.getInvalidPostById();
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, result);
    }

    @Test
    public void getInvalidPost(){
        RequestUtils.getInvalidPostById();
        ResponseUtils.validateResponseByJsonSchema();
    }

    @Test
    public void validateStatusCodeNonExistingPost(){
        RequestUtils.getNonExistingPostById();
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, result);
    }

    @Test
    public void getNonExistingPost(){
        RequestUtils.getNonExistingPostById();
        ResponseUtils.validateResponseByJsonSchema();
    }

}
