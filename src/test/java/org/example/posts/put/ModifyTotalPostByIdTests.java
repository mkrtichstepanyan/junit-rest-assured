package org.example.posts.put;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.example.models.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ModifyTotalPostByIdTests {
    RequestSpecification putSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("PUT");
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @ParameterizedTest
    @CsvSource(
            {
                    "12, Title 9, Author 9"
            }
    )
    public void validatePostModification(int id, String title, String author) {
        Post expectedPost = new Post(id, title, author);
        String jsonBody = RequestUtils.getJsonStringByObject(expectedPost);
        RequestUtils.put(putSpec, "/posts/", id, jsonBody);
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, result);

        RequestUtils.get(getSpec, "/posts/", id);
        Post actualPost = ResponseUtils.getObjectByJsonString(Post.class);
        Assertions.assertEquals(expectedPost, actualPost);
    }
}
