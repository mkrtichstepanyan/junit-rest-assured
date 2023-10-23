package org.example.comments.post.positiverequests;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.example.models.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CreateCommentPositiveTests {
    RequestSpecification postSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("POST");
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    public static final String VALIDFILE = "/csvfiles/comment/validCommentValues.csv";

    @ParameterizedTest
    @CsvFileSource(resources = VALIDFILE)
    public void validateCommentCreation(int id, String body, int postId){
        Comment expectedComment = new Comment(id, body, postId);
        String jsonStringByObject = RequestUtils.getJsonStringByObject(expectedComment);
        RequestUtils.post(postSpec, "/comments", jsonStringByObject);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(201, statusCode);

        RequestUtils.get(getSpec, "/comments/", id);
        Comment actualComment = ResponseUtils.getObjectByJsonString(Comment.class);
        Assertions.assertEquals(expectedComment, actualComment);
    }

}
