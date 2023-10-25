package org.example.posts.post.positiveTests;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.example.models.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CreatePostsPositiveTests {

    RequestSpecification postSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("POST");
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    public void validatePostCreation() {
        Post post = new Post(10, "Title3", "Author3");
        String jsonStringByObject = RequestUtils.getJsonStringByObject(post);
        RequestUtils.post(postSpecs, "/posts", jsonStringByObject);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(201, statusCode);
    }


    @ParameterizedTest
    @CsvSource(
            {
                    "6,Title6,Author6",
                    "7,Title7,Author7"
            }
    )
    public void validatePostCreation(int id, String title, String author) {
        Post expectedPost = new Post(id, title, author);
        String jsonStringByObject = RequestUtils.getJsonStringByObject(expectedPost);
        RequestUtils.post(postSpecs, "/posts", jsonStringByObject);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(201, statusCode);

        RequestUtils.get(getSpecs, "/posts/", id);
        Post actualPost = ResponseUtils.getObjectByJsonString(Post.class);
        Assertions.assertEquals(expectedPost, actualPost);
    }

}
