package org.example.comments.put;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecification.RequestSpecificationProvider;
import org.example.models.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UpdatingCommentsTests {
    RequestSpecification putSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("PUT");
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    @DisplayName("Updating comment fields and checking status code ")
    public void validateCommentUpdatingStatusCode() {
        Comment comment = new Comment(4, "new comment by put method", 50);
        String jsonStringByObject = RequestUtils.getJsonStringByObject(comment);

        RequestUtils.put(putSpecs, "/comments/", jsonStringByObject, 4);
        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);
    }

    @Test
    @DisplayName("Updating comment, checking status code and body")
    public void validateCommentUpdating() {
        Comment comment = new Comment(1, "new comment by put method----", 2);

        String jsonStringByObject = RequestUtils.getJsonStringByObject(comment);

        RequestUtils.put(putSpecs, "/comments/", jsonStringByObject, 4);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);

        RequestUtils.get(getSpecs, "/comments/", 4);

        Comment expectedComment = ResponseUtils.getObjectByJsonString(Comment.class);

        Assertions.assertEquals(comment, expectedComment);
    }
}
