package org.example.posts.get.positiv;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetPostWithParametersOfVariedObjectsTests {

    @Test
    public void validateStatusCode(){
        RequestUtils.getPostWithOfVaredParameters();
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404,result);
    }

    @Test
    public void getPostWithParametersOfVariedObjects(){
        RequestUtils.getPostWithOfVaredParameters();
        ResponseUtils.validateResponseByJsonSchema();
    }
}
