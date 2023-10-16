package org.example.comments.post;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.models.Comment;
import org.example.models.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CreateCommentsPositiveTests {

    @Test
    public void validateCommentCreation() {

        Comment comment = new Comment(2, "Title2", 2);

        String jsonStringByObject = RequestUtils.getJsonStringByObject(comment);

        RequestUtils.post("/comments", jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(201, statusCode);
    }


    @ParameterizedTest
    @CsvSource(
            {
                    "3,Title3,3",
                    "4,Title4,4"
            }
    )
    public void validateCommentCreation(int id, String body, int postId) {

        Comment actualComment = new Comment(id, body, postId);

        String jsonStringByObject = RequestUtils.getJsonStringByObject(actualComment);

        RequestUtils.post("/comments", jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(201, statusCode);

        RequestUtils.get("/comments", id);

        Comment expectedComment = ResponseUtils.getObjectByJsonString(Comment.class);

        Assertions.assertEquals(expectedComment, actualComment);
    }

}
