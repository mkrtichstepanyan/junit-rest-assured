package org.example.posts.get.getById;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.models.PostDataModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GetByIdPositiveTests {

    @Test
    @DisplayName("Request post by id then verify status code is 200")
    public void validateStatusCode() {
        RequestUtils.getPostById();
        Assertions.assertEquals(200, ResponseUtils.getStatusCode());
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());
    }

    @Test
    @DisplayName("Request post by id then verify post id")
    public void getPostById() {
        int expectedId = 1;
        RequestUtils.getPostById(expectedId);
        PostDataModel actualPostDataModel = ResponseUtils.jsonToObject(PostDataModel.class);

        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());
        Assertions.assertEquals(expectedId, actualPostDataModel.getId());
    }

    @Test
    @DisplayName("Request post by id then validate by schema")
    public void validateResponseBySchema(){
        RequestUtils.getPostById(1);
        ResponseUtils.validateGetByIdResponseByJsonSchema();
    }

    @BeforeEach
    public void clean() {
        RequestUtils.setResponse(null);
    }
}
