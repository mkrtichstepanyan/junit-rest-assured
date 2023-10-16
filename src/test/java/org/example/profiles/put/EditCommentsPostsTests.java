package org.example.posts.put;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.models.Comment;
import org.example.models.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EditCommentsPostsTests {
    @Test
    public void validateTitleEdition() {
        int id = 1;
        RequestUtils.get("/posts", id);

        Post post = ResponseUtils.getObjectByJsonString(Post.class);

        post.title = "title";

        String jsonStringByObject = RequestUtils.getJsonStringByObject(post);

        RequestUtils.put("/posts/1", jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);


        Post expectedPost = ResponseUtils.getObjectByJsonString(Post.class);

        Assertions.assertEquals(expectedPost, post);
    }


    @Test
    public void validateAuthorEdition() {
        int id = 1;
        RequestUtils.get("/posts", id);

        Post post = ResponseUtils.getObjectByJsonString(Post.class);

        post.author = "Tumanyan";

        String jsonStringByObject = RequestUtils.getJsonStringByObject(post);

        RequestUtils.put("/posts/1", jsonStringByObject);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(200, statusCode);


        Post expectedPost = ResponseUtils.getObjectByJsonString(Post.class);

        Assertions.assertEquals(expectedPost, post);
    }
}
