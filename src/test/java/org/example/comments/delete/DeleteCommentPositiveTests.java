package org.example.comments.delete;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.common.Creater;
import org.example.models.CommentDataModel;
import org.junit.jupiter.api.*;

public class DeleteCommentPositiveTests {

    CommentDataModel comment;

    @BeforeEach
    public void start(){
        System.out.println("create comment");
        comment = Creater.createComment(1);
    }
    @Test
    @DisplayName("delete comment, then validate status code")
    public void deleteComment(){

        RequestUtils.addComment(comment);
        RequestUtils.deleteComment(comment.getId());
        Assertions.assertEquals(200, ResponseUtils.getStatusCode());
    }
    @AfterEach
    public void clear(){
        RequestUtils.setResponse(null);
    }
}
