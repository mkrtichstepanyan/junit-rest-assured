package org.example.comments.patch;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.models.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EditCommentsPositiveTests {
    @Test
    public void validateBodyEdition() {

        RequestUtils.patch("/comments/1", "{\n" +
                "    \"body\": \"body changed with patch\"\n" +
                "}");

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);

        RequestUtils.get("/comments", 1);

        Comment comment = ResponseUtils.getObjectByJsonString(Comment.class);

        Assertions.assertEquals("body changed with patch", comment.body);


    }


    @Test
    public void validatePostIdEdition() {

        RequestUtils.patch("/comments/1", "{\n" +
                "    \"postId\": \"11\"\n" +
                "}");

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);

        RequestUtils.get("/comments", 1);

        Comment comment = ResponseUtils.getObjectByJsonString(Comment.class);

        Assertions.assertEquals(11, comment.postId);

    }
}
