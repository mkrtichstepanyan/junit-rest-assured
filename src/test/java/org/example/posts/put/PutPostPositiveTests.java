package org.example.posts.put;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PutPostPositiveTests {
    @Test
    public void changePostByIdWithPut(){
        RequestUtils.changePostByIdWithPut();
        Assertions.assertEquals(200, ResponseUtils.getStatusCode());
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());
    }

}
