package org.example.comments.get;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.pojos.comments.CommentsRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GetCommentsPositiveTests {

    public String endpoint = "/comments/";


    @Test
    public void validateStatusCode() {
        RequestUtils.get(endpoint);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    public void validateResponseByJsonSchema() {
        RequestUtils.get(endpoint);
        ResponseUtils.validateResponseByJsonSchema("validatorschemas/getAllCommentsValidatorSchema.json");
    }

    @Test
    public void validateGetAllComments() {
        RequestUtils.get(endpoint);
        List<CommentsRoot> commentsList = ResponseUtils.getObjectByJsonString(List.class);
        Assertions.assertNotNull(commentsList);
        Assertions.assertFalse(commentsList.isEmpty());

    }

    @Test
    public void validateGetCommentById() {
        int actualId = 1;
        RequestUtils.get(endpoint, actualId);
        CommentsRoot expectedComment = ResponseUtils.getObjectByJsonString(CommentsRoot.class);

        Assertions.assertEquals(expectedComment.id, actualId);
        Assertions.assertFalse(expectedComment.body.isEmpty());
        Assertions.assertTrue(expectedComment.postId > 0);
    }

    @Test
    public void validateGetCommentByParam() {
        String paramKey = "postId";
        String paramValue = "1";
        RequestUtils.get(endpoint, paramKey, paramValue);
        String expectedValue = ResponseUtils.getStringValueByJsonPath(paramKey);

        Assertions.assertTrue(expectedValue.matches(paramValue));
    }
}
