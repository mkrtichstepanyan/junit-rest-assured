package org.example.posts.get.nonexisting;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetPostByNonExistingTitleTests {
    @Test
    public void validateStatusCodeNonExistingTitle(){
        RequestUtils.getPostByNonExistingTitle();
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, result);
    }

    @Test
    public void getNonExistingTitle(){
        RequestUtils.getPostByNonExistingTitle();
        ResponseUtils.validateResponseByJsonSchema();
    }
}
