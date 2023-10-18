package org.example.posts.get.positiv;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetTwoPostsByIdsPositiveTests {

    @Test
    public void validateStatusCode(){
        RequestUtils.getTwoPostsByValidIds();
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, result);
    }

    @Test
    public void getTwoPostsByIds(){
        RequestUtils.getTwoPostsByValidIds();
        ResponseUtils.validateResponseByJsonSchema();
    }
}
