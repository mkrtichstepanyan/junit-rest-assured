package org.example.posts.post.negativeTests;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.example.models.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CreatePostNegativeTests {

    RequestSpecification postSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("POST");
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");
    public static final String ENDPOINT = "/posts";
    public static final String FILE_WITH_REDUNDANT_PARAM = "/csvfiles/redundants/post_with_redundant_parameter.csv";

    @Test
    public void validateCreatePostByEmptyBody() {
        Post expectedPost = new Post();
        String jsonBody = RequestUtils.getJsonStringByObject(expectedPost);
        RequestUtils.post(postSpec, ENDPOINT, jsonBody);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);

        Post actualPost = ResponseUtils.getObjectByJsonString(Post.class);
        Assertions.assertEquals(expectedPost, actualPost);
    }

    @ParameterizedTest
    @CsvFileSource(resources = FILE_WITH_REDUNDANT_PARAM)
    public void validateCreatePostByRedundant(int id, String title, String author, String redundantParam) {
        Post expectedPost = new Post(id, title, author, redundantParam);
        String jsonBody = RequestUtils.getJsonStringByObject(expectedPost);
        RequestUtils.post(postSpec, ENDPOINT, jsonBody);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);

        RequestUtils.get(getSpec, ENDPOINT, id);
        int statusCode2 = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode2);
    }
}
