package org.example.posts.post;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataProviders.requestSpecifications.RequestSpecificationProvider;
import org.example.models.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CreatePostsPositiveTests {
    public String endpoint = "/posts/";
    RequestSpecification postSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("POST");

    @Test
    public void validatePostCreation() {

        Post post = new Post(3, "Title3", "Author3");

        String jsonStringByObject = RequestUtils.getJsonStringByObject(post);

        RequestUtils.post(postSpecs, endpoint, jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(201, statusCode);
    }


    @ParameterizedTest
    @CsvSource(
            {
                    "4,Title4,Author4",
                    "5,Title5,Author5"
            }
    )
    public void validatePostCreation(int id, String title, String author) {

        Post actualPost = new Post(id, title, author);

        String jsonStringByObject = RequestUtils.getJsonStringByObject(actualPost);

        RequestUtils.post(postSpecs, endpoint, jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(201, statusCode);

        RequestUtils.get(postSpecs, endpoint, id);

        Post expectedPost = ResponseUtils.getObjectByJsonString(Post.class);

        Assertions.assertEquals(actualPost, expectedPost);
    }

}
