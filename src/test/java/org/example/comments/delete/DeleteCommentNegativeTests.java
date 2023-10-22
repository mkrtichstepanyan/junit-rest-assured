package org.example.comments.delete;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecification.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class DeleteCommentNegativeTests {
    RequestSpecification deleteSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("DELETE");

    @Test
    @DisplayName("Deleting comment by non existing id number")
    public void validateCommentDeletion() {
        RequestUtils.delete(deleteSpecs, "/comments/", 100);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);
    }

}
