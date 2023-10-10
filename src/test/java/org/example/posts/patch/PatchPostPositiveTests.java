package org.example.posts.patch;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatchPostPositiveTests {
    @Test
    public void changePostByIdWithPatch(){
        RequestUtils.changePostByIdWithPatch();
        Assertions.assertEquals(200, ResponseUtils.getStatusCode());
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());
    }
}
