package org.example.posts.delete;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeletePostPositiveTests {

    @Test
    public void deletePostById(){
        RequestUtils.deletePostById();
        Assertions.assertEquals(200,ResponseUtils.getStatusCode());
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());
    }
}
