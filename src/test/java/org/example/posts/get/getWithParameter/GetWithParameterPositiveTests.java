package org.example.posts.get.getWithParameter;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetWithParameterPositiveTests {
    @Test
    public void getPostWithParameter(){
        RequestUtils.getPostWithParameter();
        Assertions.assertEquals(200, ResponseUtils.getStatusCode());
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());
    }
}
