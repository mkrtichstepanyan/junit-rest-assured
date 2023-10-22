package org.example.posts.delete;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecification.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class DeletePostsTests {
    RequestSpecification deleteSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("DELETE");
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    @DisplayName("Deleting post by id number")
    public void validatePostDeletion() {
        RequestUtils.delete(deleteSpecs, "/posts/", 44);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
        RequestUtils.get(getSpecs, "/posts/", 44);
        int statusCode1 = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode1);
    }


    @ParameterizedTest
    @DisplayName("Deleting several posts by id numbers")
    @CsvSource({"1", "50"})
    public void validatePostDeletion(int id) {
        RequestUtils.delete(deleteSpecs,"/posts/", id);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
        RequestUtils.get(getSpecs, "/posts/", id);
        int statusCode1 = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode1);
    }
}
