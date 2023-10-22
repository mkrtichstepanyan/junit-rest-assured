package org.example.posts.put;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecification.RequestSpecificationProvider;
import org.example.models.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UpdatingPostsTests {
    RequestSpecification putSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("PUT");
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    @DisplayName("Updating post and checking status code ")
    public void validatePostUpdatingStatusCode() {
        Post post = new Post(70, "new title by put", "new author");
        String jsonStringByObject = RequestUtils.getJsonStringByObject(post);

        RequestUtils.put(putSpecs, "/posts/", jsonStringByObject, 70);
        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);
    }

}
