package org.example.posts.get;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetAllPostsNegativeTests {

    @Test
    public void validateStatusCodeForWrongID() {
        RequestUtils.get("/posts/-1");
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);
    }

    @Test
    public void getPostByWrongID() {
        RequestUtils.get("/posts",-1);
        int responseBody = ResponseUtils.getStringValueByJsonPath("/posts").length();
        Assertions.assertEquals(2, responseBody);
    }
}
