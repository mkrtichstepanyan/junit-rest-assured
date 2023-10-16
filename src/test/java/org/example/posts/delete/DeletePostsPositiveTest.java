package org.example.posts.delete;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DeletePostsPositiveTest {

    @Test
    public void validatePostDeletion() {

        RequestUtils.delete("/posts/7");

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);


    }


    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    public void validateCommentDeletion(int id) {

        RequestUtils.delete("/posts/" + id);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);
    }
}