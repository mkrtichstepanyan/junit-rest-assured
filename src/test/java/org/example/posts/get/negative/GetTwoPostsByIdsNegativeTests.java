package org.example.posts.get.negative;

import io.restassured.internal.common.assertion.Assertion;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetTwoPostsByIdsNegativeTests {

    @Test
    public void validateStatusCode(){
        RequestUtils.getTwoPostsByValidAndInvalidIds();
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, result);
    }

    @Test
    public void getTwoPostsByIds(){
        RequestUtils.getTwoPostsByValidAndInvalidIds();
        ResponseUtils.validateResponseByJsonSchema();
    }
}
