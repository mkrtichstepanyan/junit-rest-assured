package org.example.profiles.post;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.models.Post;
import org.example.models.Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CreatePostsPositiveTests {

    @Test
    public void validatePostCreation() {

        Profile profile = new Profile("New");

        String jsonStringByObject = RequestUtils.getJsonStringByObject(profile);

        RequestUtils.post("/profile", jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(201, statusCode);
    }


    @ParameterizedTest
    @CsvSource(
            {
                    "6,Title4,Author4",
                    "7,Title5,Author5"
            }
    )
    public void validatePostCreation(int id, String title, String author) {

        Post actualPost = new Post(id, title, author);

        String jsonStringByObject = RequestUtils.getJsonStringByObject(actualPost);

        RequestUtils.post("/posts", jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(201, statusCode);

        RequestUtils.get("/posts", id);

        Post expectedPost = ResponseUtils.getObjectByJsonString(Post.class);

        Assertions.assertEquals(expectedPost, actualPost);
    }

}
