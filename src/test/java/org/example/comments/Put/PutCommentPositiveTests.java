package org.example.comments.Put;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.models.CommentDataModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PutCommentPositiveTests {

    @Test
    public void validateStatusCode(){
        CommentDataModel commentDataModel = new CommentDataModel(2,"comment_222",222);
        RequestUtils.putComment(commentDataModel);
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());

        Assertions.assertEquals(200, ResponseUtils.getStatusCode());
    }

    @Test
    @DisplayName("put comment,then validate comments body")
    public void validateCommentsBody(){
        CommentDataModel commentDataModel = new CommentDataModel(2,"comment_222",222);
        RequestUtils.putComment(commentDataModel);
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());

        Assertions.assertEquals(200, ResponseUtils.getStatusCode());


        RequestUtils.getCommentById(commentDataModel.getId());
        CommentDataModel expectedCommentDataModel = ResponseUtils.jsonToObject(CommentDataModel.class);
        Assertions.assertEquals(commentDataModel.getBody(),expectedCommentDataModel.getBody());
    }
}
