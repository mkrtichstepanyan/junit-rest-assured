package org.example.posts.post;

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
                    "6,Title4,Author4",
                    "6,Title5,Author5"
            }
    )
    public void validatePostCreation(int id, String title, String author) {

        Post actualPost = new Post(id, title, author);

        String jsonStringByObject = RequestUtils.getJsonStringByObject(actualPost);

        RequestUtils.post(postSpecs, "/posts", jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(201, statusCode);


        RequestUtils.get(getSpecs, "/posts", id);

        Post expectedPost = ResponseUtils.getObjectByJsonString(Post.class);

        Assertions.assertEquals(actualPost, expectedPost);
    }

}
