package org.example.comments.delete;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteCommentPositiveTests {

    public String endpoint = "/comments/";

    @Test
    public void validateDeleteComment() {
        int id = 4;
        RequestUtils.delete(endpoint, id);

        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
    }
}
