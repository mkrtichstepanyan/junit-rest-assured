package org.example.comments.post;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.common.Creater;
import org.example.models.CommentDataModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateCommentsPositiveTests {
    CommentDataModel comment;
    @BeforeEach
    public void start(){
        System.out.println("create Comment");
        comment = Creater.createComment(2);
    }
    @Test
    public void validateStatusCode(){
        RequestUtils.addComment(comment);
        Assertions.assertEquals(201, ResponseUtils.getStatusCode());
    }

    @AfterEach
    public void clear(){
        System.out.println("delete Comment");
        RequestUtils.deleteComment(comment);
        RequestUtils.setResponse(null);

    }
}
