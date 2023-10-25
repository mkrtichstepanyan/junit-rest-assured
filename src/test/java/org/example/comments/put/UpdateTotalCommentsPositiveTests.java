package org.example.comments.put;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.example.models.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class UpdateTotalCommentsPositiveTests {
    RequestSpecification putSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("PUT");
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");
    public static final String ENDPOINT = "/comments";

    @ParameterizedTest
    @CsvSource(
            {
                    "4, updated body, 50"
            }
    )
    public void validateCommentModification(int id, String body, int postId) {
        Comment expectedComment = new Comment(id, body, postId);
        String jsonBody = RequestUtils.getJsonStringByObject(expectedComment);
        RequestUtils.put(putSpec, ENDPOINT, id, jsonBody);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);

        RequestUtils.get(getSpec, ENDPOINT, id);
        Comment actualComment = ResponseUtils.getObjectByJsonString(Comment.class);
        Assertions.assertEquals(expectedComment, actualComment);
    }
}
