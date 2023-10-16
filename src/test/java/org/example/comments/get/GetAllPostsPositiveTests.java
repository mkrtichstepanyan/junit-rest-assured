package org.example.comments.get;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.models.Comment;
import org.example.pojos.posts.PostsRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetAllPostsPositiveTests {

    @Test
    public void validateStatusCode() {
        RequestUtils.get("/comments");
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    public void validateResponseByJsonSchema() {
        RequestUtils.get("/comments");
        ResponseUtils.validateResponseByJsonSchema("validatorschemas/getAllCommentsValidatorSchema.json");
    }


    @Test
    public void validateGetCommentById() {
        RequestUtils.get("/comments", 1);

        Comment objectByJsonString = ResponseUtils.getObjectByJsonString(Comment.class);

        ResponseUtils.getStringValueByJsonPath("id");
    }
}
