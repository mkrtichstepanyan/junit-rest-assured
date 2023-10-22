package org.example.comments.patch;


import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecification.RequestSpecificationProvider;
import org.example.models.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UpdatingCommentsFieldTests {
    RequestSpecification patchSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("PATCH");
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    @DisplayName("Updating comment field and checking status code")
    public void validateCommentFieldUpdatingStatusCode() {
        RequestUtils.get(getSpecs,"/comments/",2);
        Comment comment =ResponseUtils.getObjectByJsonString(Comment.class);
        comment.body = "new comment by patch";
        comment.postId = 7;
        String jsonStringByObject = RequestUtils.getJsonStringByObject(comment);

        RequestUtils.patch(patchSpecs, "/comments/", jsonStringByObject, 2);
        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);
    }
}

