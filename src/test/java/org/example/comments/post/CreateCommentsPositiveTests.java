package org.example.comments.post;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataProviders.requestSpecifications.RequestSpecificationProvider;
import org.example.models.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CreateCommentsPositiveTests {
    public String endpoint = "/comments/";
    RequestSpecification postSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("POST");
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    public void validateCommentCreation() {
        int commentId = 2;
        String commentBody = "Comment2";
        int postId = 2;

        Comment createdComment = new Comment(commentId, commentBody, postId);
        String jsonStringByObject = RequestUtils.getJsonStringByObject(createdComment);

        RequestUtils.post(postSpecs, endpoint, jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(201, statusCode);

        RequestUtils.get(getSpecs, endpoint, commentId);
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

        RequestUtils.post(postSpecs, endpoint, jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(201, statusCode);

        RequestUtils.get(getSpecs, endpoint, commentId);
        Comment expectedComment = ResponseUtils.getObjectByJsonString(Comment.class);

        Assertions.assertEquals(createdComment, expectedComment);
    }
}
