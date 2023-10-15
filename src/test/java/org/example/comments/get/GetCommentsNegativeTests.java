package org.example.comments.get;

import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.response.ValidatableResponse;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.pojos.comments.CommentsRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetCommentsNegativeTests {

    public String endpoint = "/comments/";


    @Test
    public void validateStatusCodeForWrongID() {
        RequestUtils.get(endpoint, -1);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);
    }


    //question
    //if Comment.class is empty getObjectByJsonString will return the object with fields set to default values
    //is it a good practice to check each field is the default value or there is another way to we can check for wrong id we receive empty json
    @Test
    public void getCommentByWrongID() {
        RequestUtils.get(endpoint, 500);
        CommentsRoot expectedComment = ResponseUtils.getObjectByJsonString(CommentsRoot.class);

        Assertions.assertEquals(expectedComment.id, 0);
        Assertions.assertNull(expectedComment.body);
        Assertions.assertEquals(expectedComment.postId, 0);
    }
}
