package org.example.comments.patch.put;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.models.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EditCommentsPositiveTests {
    @Test
    public void validateBodyEdition() {
        int id = 1;
        RequestUtils.get("/comments", id);

        Comment comment = ResponseUtils.getObjectByJsonString(Comment.class);

        comment.body = "10";

        String jsonStringByObject = RequestUtils.getJsonStringByObject(comment);

        RequestUtils.put("/comments/1", jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);
    }


    @Test
    public void validatePostIdEdition() {
        int id = 1;
        RequestUtils.get("/comments", id);

        Comment comment = ResponseUtils.getObjectByJsonString(Comment.class);

        comment.postId = 10;

        String jsonStringByObject = RequestUtils.getJsonStringByObject(comment);

        RequestUtils.put("/comments/1", jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);
    }
}
