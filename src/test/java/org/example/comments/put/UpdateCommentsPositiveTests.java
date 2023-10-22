package org.example.comments.put;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataProviders.requestSpecifications.RequestSpecificationProvider;
import org.example.models.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UpdateCommentsPositiveTests {

    public String endpoint = "/comments/";
    RequestSpecification putSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("PUT");
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    public void validateCommentUpdate() {
        int commentId = 2;
        String newBody = "Comment2 updated";
        int postId = 2;

        Comment updatedComment = new Comment(commentId, newBody, postId);
        String jsonStringByObject = RequestUtils.getJsonStringByObject(updatedComment);

        RequestUtils.put(putSpecs, endpoint + commentId, jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);

        RequestUtils.get(getSpecs, endpoint, commentId);
        Comment expectedComment = ResponseUtils.getObjectByJsonString(Comment.class);

        Assertions.assertEquals(updatedComment, expectedComment);
    }
}
