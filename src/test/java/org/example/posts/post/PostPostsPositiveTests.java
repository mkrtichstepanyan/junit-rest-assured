package org.example.posts.post;

import io.restassured.response.ResponseBodyExtractionOptions;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.api.model.PostDataModel;
import org.junit.jupiter.api.*;

public class PostPostsPositiveTests {

    PostDataModel postDataModel;

    @BeforeEach
    public void start() {
        System.out.println("before");
        postDataModel = createPost(4);
    }


    @Test
    @DisplayName("Request add post then validate stratus code is 201")
    public void validateStatusCode() {
        RequestUtils.addPost(postDataModel);
        int statusCode = ResponseUtils.getStatusCode();
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());
        Assertions.assertEquals(201, statusCode);
    }

    @Test
    @DisplayName("Request add post then validate id")
    public void validatePostId() {
        RequestUtils.addPost(postDataModel);
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());

        ResponseBodyExtractionOptions body = RequestUtils.getResponse().extract().body();
        PostDataModel newPostDataModel = body.as(PostDataModel.class);
        Assertions.assertEquals(postDataModel.getId(), newPostDataModel.getId());
    }

    @Test
    @DisplayName("Request add post then validate all post")
    public void validateAllPost() {
        RequestUtils.addPost(postDataModel);
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());

        ResponseBodyExtractionOptions body = RequestUtils.getResponse().extract().body();
        PostDataModel newPostDataModel = body.as(PostDataModel.class);
        Assertions.assertEquals(postDataModel, newPostDataModel);
    }

    @AfterEach
    public void clear() {
        System.out.println("after");
        RequestUtils.setResponse(null);
        RequestUtils.deletePost(postDataModel);
    }

    private PostDataModel createPost(int id) {
        PostDataModel postDataModel = new PostDataModel();
        postDataModel.setId(id);
        postDataModel.setTitle("json-server_" + id);
        postDataModel.setAuthor("typicode_" + id);
        return postDataModel;
    }

}
