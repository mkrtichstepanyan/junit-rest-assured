package org.example.comments.patch;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataProviders.requestSpecifications.RequestSpecificationProvider;
import org.example.models.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatchCommentsPositiveTests {

    public String endpoint = "/comments/";
    RequestSpecification patchSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("PATCH");
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    public void validateCommentUpdate() {
        int commentId = 3;
        String newBody = "Comment 333";

        RequestUtils.get(getSpecs, endpoint, commentId);
        Comment existingComment = ResponseUtils.getObjectByJsonString(Comment.class);
        existingComment.body = newBody;

        String patchData = RequestUtils.getJsonStringByObject(existingComment);

        RequestUtils.patch(patchSpecs, endpoint + commentId, patchData);

        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);

        RequestUtils.get(getSpecs, endpoint, commentId);
        Comment expectedComment = ResponseUtils.getObjectByJsonString(Comment.class);

        Assertions.assertEquals(existingComment, expectedComment);
    }
}
