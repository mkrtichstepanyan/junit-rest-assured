package org.example.comments.post;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.models.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CreateCommentsPositiveTests {
    public String endpoint = "/comments/";

    @Test
    public void validateCommentCreation() {
        int commentId = 2;
        String commentBody = "Comment2";
        int postId = 2;

        Comment createdComment = new Comment(commentId, commentBody, postId);
        String jsonStringByObject = RequestUtils.getJsonStringByObject(createdComment);

        RequestUtils.post(endpoint, jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(201, statusCode);

        RequestUtils.get(endpoint, commentId);
        Comment expectedComment = ResponseUtils.getObjectByJsonString(Comment.class);

        Assertions.assertEquals(createdComment, expectedComment);
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "3,Comment 3,1",
                    "4,Comment 4,2"
            }
    )
    public void validateCommentsCreation(int commentId, String commentBody, int postId) {

        Comment createdComment = new Comment(commentId, commentBody, postId);
        String jsonStringByObject = RequestUtils.getJsonStringByObject(createdComment);

        RequestUtils.post(endpoint, jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(201, statusCode);

        RequestUtils.get(endpoint, commentId);
        Comment expectedComment = ResponseUtils.getObjectByJsonString(Comment.class);

        Assertions.assertEquals(createdComment, expectedComment);
    }
}
