package org.example.comments.patch;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecification.RequestSpecificationProvider;
import org.example.models.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UpdatingCommentFieldsNegativeTests {
    RequestSpecification patchSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("PATCH");
    @Test
    @DisplayName("Updating comment field with non existing id number")
    public void validateCommentFieldUpdatingStatusCode() {
        Comment comment = new Comment();
        comment.body = "new comment by patch ";
        comment.postId = 2;
        String jsonStringByObject = RequestUtils.getJsonStringByObject(comment);

        RequestUtils.patch(patchSpecs, "/comments/", jsonStringByObject, 101);
        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(404, statusCode);
    }

}
