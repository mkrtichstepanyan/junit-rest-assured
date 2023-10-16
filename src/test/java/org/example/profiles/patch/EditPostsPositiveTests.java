package org.example.profiles.patch;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.models.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EditPostsPositiveTests {
    @Test
    public void validateAuthorEdition() {

        RequestUtils.patch("/posts/1", "{\n" +
                "    \"author\": \"author changed with patch\"\n" +
                "}");

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);

        RequestUtils.get("/posts", 1);

        Post post = ResponseUtils.getObjectByJsonString(Post.class);

        Assertions.assertEquals("author changed with patch", post.author);


    }


    @Test
    public void validateTitleEdition() {

        RequestUtils.patch("/posts/1", "{\n" +
                "    \"title\": \"title changed with patch\"\n" +
                "}");

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);

        RequestUtils.get("/posts", 1);

        Post post = ResponseUtils.getObjectByJsonString(Post.class);

        Assertions.assertEquals("title changed with patch", post.title);

    }
}