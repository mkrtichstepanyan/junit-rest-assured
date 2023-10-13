package org.example.posts.get.getAll;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.response.ValidatableResponse;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.models.PostDataModel;
import org.example.pojos.posts.PostsRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GetAllPostsPositiveTests {

    @Test
    public void validateStatusCode() {
        RequestUtils.getAllPosts();
        int statusCode = ResponseUtils.getStatusCode();
        System.out.println( ResponseUtils.getResponse().extract().asPrettyString());
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    @DisplayName("Request all post then validate size posts list")
    public void validateGetAllPostsListSize(){
        RequestUtils.getAllPosts();
        ValidatableResponse response = ResponseUtils.getResponse();
        ResponseBodyExtractionOptions body = response.extract().body();
        List<PostDataModel> postsList = body.as(new TypeRef<>() {});
        Assertions.assertEquals(3,postsList.size());
    }

    @Test
    @DisplayName("test pojo class")
    public void validateGetPostById() {
        RequestUtils.getPostById(1);

        PostsRoot objectByJsonString = ResponseUtils.jsonToObject(PostsRoot.class);

        ResponseUtils.getStringValueByJsonPath("id");
        ResponseUtils.getStringValueByJsonPath("user.name");
    }

    @Test
    public void validateResponseByJsonSchema() {
        RequestUtils.getAllPosts();
        ResponseUtils.validateGetAllResponseByJsonSchema();
    }

    @BeforeEach
    public void tearDown(){
        RequestUtils.setResponse(null);
    }
}
