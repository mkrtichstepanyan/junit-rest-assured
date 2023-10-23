package org.example.comments.post;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.example.models.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CreateCommentPositiveTests {

    RequestSpecification postSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("POST");
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");
    public static final String VALID_COMMENT_VALUES_FILE = "/csvfiles/valid_comment_values.csv";
    public static final String ENDPOINT = "/comments";

    @ParameterizedTest
    @CsvFileSource(resources = VALID_COMMENT_VALUES_FILE)
    public void validateCommentCreation(int id, String body, int postId) {
        Comment expectedComment = new Comment(id, body, postId);
        String jsonBody = RequestUtils.getJsonStringByObject(expectedComment);
        RequestUtils.post(postSpecs, ENDPOINT, jsonBody);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(201, statusCode);

        RequestUtils.get(getSpecs, ENDPOINT, id);
        Comment actualComment = ResponseUtils.getObjectByJsonString(Comment.class);
        Assertions.assertEquals(expectedComment, actualComment);
    }
}
