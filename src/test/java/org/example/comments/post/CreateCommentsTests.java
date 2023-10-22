package org.example.comments.post;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecification.RequestSpecificationProvider;
import org.example.models.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CreateCommentsTests {
    RequestSpecification postSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("POST");
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    @DisplayName("Creating comment and checking status code ")
    public void validateCommentCreation() {

        Comment comment = new Comment(1, "Comment1", 4);

        String jsonStringByObject = RequestUtils.getJsonStringByObject(comment);

        RequestUtils.post(postSpecs, "/comments", jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(201, statusCode);
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "6,comment3,4",
                    "7,comment3,2"
            }
    )
    public void validateCommentCreation(int id, String body, int postId) {

        Comment actualComment = new Comment(id, body, postId);

        String jsonStringByObject = RequestUtils.getJsonStringByObject(actualComment);

        RequestUtils.post(postSpecs, "/comments", jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(201, statusCode);

        RequestUtils.get(getSpecs, "/comments", id);

        Comment expectedComment = ResponseUtils.getObjectByJsonString(Comment.class);

        Assertions.assertEquals(actualComment, expectedComment);
    }

}
