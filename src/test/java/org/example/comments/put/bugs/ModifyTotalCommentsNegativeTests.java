package org.example.comments.put.bugs;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.example.models.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ModifyTotalCommentsNegativeTests {

    RequestSpecification putSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("PUT");
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @ParameterizedTest
    @CsvSource(
            {
                    "2, updated comment, 212"
            }
    )
    public void validateCommentModificationByWrongPostId(int id, String body, int postId) {
        Comment expectedComment = new Comment(id, body, postId);
        String jsonStringBody = RequestUtils.getJsonStringByObject(expectedComment);
        RequestUtils.put(putSpec, "/comments/", id, jsonStringBody);
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, result);

        RequestUtils.get(getSpec, "/comments/", id);
        Comment actualComment = ResponseUtils.getObjectByJsonString(Comment.class);
        Assertions.assertEquals(expectedComment, actualComment);
    }


}
