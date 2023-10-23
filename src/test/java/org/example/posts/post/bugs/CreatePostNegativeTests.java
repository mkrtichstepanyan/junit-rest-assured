package org.example.posts.post.bugs;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.example.models.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CreatePostNegativeTests {

    RequestSpecification postSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("POST");
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    public static final String ENDPOINT = "/posts";

    @Test
    public void validateCreatePostWithoutBody() {
        Post expectedPost = new Post();
        String jsonStringByObject = RequestUtils.getJsonStringByObject(expectedPost);
        RequestUtils.post(postSpec, ENDPOINT, jsonStringByObject);
        int resultByStatusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, resultByStatusCode);

        Post actualPost = ResponseUtils.getObjectByJsonString(Post.class);
        Assertions.assertEquals(expectedPost, actualPost);
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "88, Title 88, Author 88, kl"
            }
    )
    public void validateCreateNewPostWithWrongKeys(int number, String name, String surname, String redundant) {
        Post expectedPost = new Post(number, name, surname, redundant);
        String jsonStringBody = RequestUtils.getJsonStringByObject(expectedPost);
        RequestUtils.post(postSpec, ENDPOINT, jsonStringBody);
        int resultByStatusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, resultByStatusCode);

        RequestUtils.get(getSpec, "/posts/", number);
        Post actualPost = ResponseUtils.getObjectByJsonString(Post.class);
        Assertions.assertEquals(expectedPost, actualPost);
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "title 909, 909"
            }
    )
    public void validateCreateNewPostWithNoFullyFields(String author, int id) {
        Post expectedPost = new Post(author, id);
        String jsonStringBody = RequestUtils.getJsonStringByObject(expectedPost);
        RequestUtils.post(postSpec, "/posts/", jsonStringBody);
        int resultByStatusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, resultByStatusCode);

        RequestUtils.get(getSpec, "/posts/", id);
        String actualPost = RequestUtils.getJsonStringByObject(Post.class);
        Assertions.assertEquals(expectedPost, actualPost);
    }

}
