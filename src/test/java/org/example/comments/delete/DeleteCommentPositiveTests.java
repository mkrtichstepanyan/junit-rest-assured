package org.example.comments.delete;

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

public class DeleteCommentPositiveTests {
    RequestSpecification deleteSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("DELETE");
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");
    RequestSpecification postSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("POST");

    @Test
    @DisplayName("Deleting comment by id number")
    public void validateCommentDeletion() {
        int id = 9;
        Comment comment = new Comment(id, "Comment6", 4);
        String jsonStringByObject = RequestUtils.getJsonStringByObject(comment);
        RequestUtils.post(postSpecs, "/comments", jsonStringByObject);

        RequestUtils.delete(deleteSpecs, "/comments/", id);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
        RequestUtils.get(getSpecs, "/comment/", id);
        int statusCode1 = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode1);
    }


    @ParameterizedTest
    @DisplayName("Deleting several comments by id numbers")
    @CsvSource({"7", "3"})
    public void validateCommentDeletion(int id) {
        RequestUtils.delete(deleteSpecs, "/comments/", id);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
    }
}
