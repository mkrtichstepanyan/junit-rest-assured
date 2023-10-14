package org.example.posts.delete;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.common.Creater;
import org.example.models.PostDataModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeletePostPositiveTests {

    PostDataModel post;
    @BeforeEach
    public void start(){
        System.out.println("create Post");
        post = Creater.createPost(4);

    }
    @Test
    public void deletePost(){
        RequestUtils.addPost(post);
        RequestUtils.deletePost(post);
        Assertions.assertEquals(200,ResponseUtils.getStatusCode());
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());
    }

    @AfterEach
    public void clear(){
        RequestUtils.setResponse(null);
    }
}
