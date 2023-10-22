package org.example.comments.get;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataProviders.requestSpecifications.RequestSpecificationProvider;
import org.example.pojos.comments.CommentsRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetCommentsPositiveTests {

    public String endpoint = "/comments/";
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");


    @Test
    public void validateStatusCode() {
        RequestUtils.get(getSpecs, endpoint);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    public void validateResponseByJsonSchema() {
        RequestUtils.get(getSpecs, endpoint);
        ResponseUtils.validateResponseByJsonSchema("validatorschemas/getAllCommentsValidatorSchema.json");
    }

    @Test
    public void validateGetAllComments() {
        RequestUtils.get(getSpecs, endpoint);
        List<CommentsRoot> commentsList = ResponseUtils.getObjectByJsonString(List.class);
        Assertions.assertNotNull(commentsList);
        Assertions.assertFalse(commentsList.isEmpty());

    }

    @Test
    public void validateGetCommentById() {
        int actualId = 1;
        RequestUtils.get(getSpecs, endpoint, actualId);
        CommentsRoot expectedComment = ResponseUtils.getObjectByJsonString(CommentsRoot.class);

        Assertions.assertEquals(expectedComment.id, actualId);
        Assertions.assertFalse(expectedComment.body.isEmpty());
        Assertions.assertTrue(expectedComment.postId > 0);
    }

    @Test
    public void validateGetCommentByParam() throws IOException {

        List<HashMap<String, Object>> params = RequestUtils.readJsonFile("src/main/java/org/example/dataProviders/CommentParamsSet.json");

        for (HashMap<String, Object> param : params) {
            RequestUtils.get(getSpecs, endpoint, param);

            List<Map<String, Object>> comments = ResponseUtils.getObjectByJsonString(List.class);

            for (Map<String, Object> comment : comments) {
                for (Map.Entry<String, Object> entry : param.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    Object expectedValue = comment.get(key);
                    Assertions.assertEquals(expectedValue, value);
                }
            }
        }
    }
}
