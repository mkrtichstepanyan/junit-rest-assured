package org.example.comments.put;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataProviders.requestSpecifications.RequestSpecificationProvider;
import org.example.models.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UpdateCommentsNegativeTests {

    public String endpoint = "/comments/";
    RequestSpecification putSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("PUT");

    @Test
    public void validateCommentUpdateForNonExistingId() {
        int commentId = 5;
        String newBody = "Comment2 updated";
        int postId = 2;

        Comment updatedComment = new Comment(commentId, newBody, postId);
        String jsonStringByObject = RequestUtils.getJsonStringByObject(updatedComment);

        RequestUtils.put(putSpecs, endpoint + commentId, jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);
    }
}
