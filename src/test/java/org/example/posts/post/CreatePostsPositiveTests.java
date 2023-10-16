package org.example.posts.post;

import io.restassured.response.ResponseBodyExtractionOptions;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.common.Creater;
import org.example.models.PostDataModel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CreatePostsPositiveTests {

    PostDataModel post;

    @BeforeEach
    public void start() {
        System.out.println("before create postDataModel");
        post = Creater.createPost(4);
    }


    @Test
    @DisplayName("Request add post then validate stratus code is 201")
    public void validateStatusCode() {
        RequestUtils.addPost(post);
        int statusCode = ResponseUtils.getStatusCode();
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());
        Assertions.assertEquals(201, statusCode);
    }

    @Test
    @DisplayName("Request add post then validate id")
    public void validatePostId() {
        RequestUtils.addPost(post);
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());

        ResponseBodyExtractionOptions body = RequestUtils.getResponse().extract().body();
        PostDataModel newPostDataModel = body.as(PostDataModel.class);
        Assertions.assertEquals(post.getId(), newPostDataModel.getId());
    }

    @Test
    @DisplayName("Request add post then validate all post")
    public void validateAllPost() {
        RequestUtils.addPost(post);
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());

        PostDataModel newPostDataModel = ResponseUtils.jsonToObject(PostDataModel.class);
        Assertions.assertEquals(post, newPostDataModel);
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "6,Title4,Author4",
                    "7,Title5,Author5"
            }
    )
    public void validatePostCreation(int id, String title, String author) {

        post = new PostDataModel(id, title, author);

        RequestUtils.addPost(post);

        int statusCode = ResponseUtils.getStatusCode();

        Assertions.assertEquals(201, statusCode);

        RequestUtils.getPostById(id);

        PostDataModel expectedPost = ResponseUtils.jsonToObject(PostDataModel.class);

        Assertions.assertEquals(post, expectedPost);
    }

    @AfterEach
    public void clear() {
        System.out.println("after delete PostDataModel");
        RequestUtils.deletePost(post);
        RequestUtils.setResponse(null);
    }


}
