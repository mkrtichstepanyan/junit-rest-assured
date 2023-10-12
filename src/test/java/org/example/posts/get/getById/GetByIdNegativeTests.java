package org.example.posts.get.getById;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GetByIdNegativeTests {

    @Test
    @DisplayName("Request post by negative id then verify status code is 404")
    public void getPostByNegativeId() {
        int expectedId = -1;
        RequestUtils.getPostById(expectedId);
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());
        Assertions.assertEquals(404, ResponseUtils.getStatusCode());
    }

    @Test
    @DisplayName("Request post by not exist id then verify status code is 404")
    public void getPostByNotExistId() {
        int expectedId = Integer.MAX_VALUE;
        RequestUtils.getPostById(expectedId);
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());
        Assertions.assertEquals(404, ResponseUtils.getStatusCode());
    }

    @BeforeEach
    public void clear(){
        RequestUtils.setResponse(null);
    }
}
