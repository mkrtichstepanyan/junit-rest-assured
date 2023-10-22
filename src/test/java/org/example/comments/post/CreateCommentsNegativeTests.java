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

public class CreateCommentsNegativeTests {
    public String endpoint = "/comments/";
    RequestSpecification postSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("POST");

    @Test
    public void validateCommentCreationWithExistingId() {
        int commentId = 2;
        String commentBody = "Comment2";
        int postId = 2;

        Comment createdComment = new Comment(commentId, commentBody, postId);
        String jsonStringByObject = RequestUtils.getJsonStringByObject(createdComment);

        RequestUtils.post(postSpecs, endpoint, jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(500, statusCode);
    }
}
