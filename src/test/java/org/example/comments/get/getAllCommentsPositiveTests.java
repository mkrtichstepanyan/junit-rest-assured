package org.example.comments.get;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class getAllCommentsPositiveTests {

    @Test
    public void validateStatusCode(){
        RequestUtils.getAllComments();
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());
        Assertions.assertEquals(200, ResponseUtils.getStatusCode());
    }

}
