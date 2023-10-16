package org.example.comments.delete;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DeleteCommentsNegativeTest {

    @Test
    public void validateCommentDeletion() {

        RequestUtils.delete("/comments/4");

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);


    }


    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    public void validateCommentDeletion(int id) {

        RequestUtils.delete("/comments/" + id);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);
    }
}