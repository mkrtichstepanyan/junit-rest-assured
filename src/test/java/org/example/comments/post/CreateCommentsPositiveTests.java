package org.example.comments.post;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.common.Creater;
import org.example.models.CommentDataModel;
import org.example.models.PostDataModel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

public class CreateCommentsPositiveTests {
    CommentDataModel comment;
//    @BeforeEach
//    public void start(){
//        System.out.println("start");
//        comment = Creater.createComment(1);
//    }
    @Test
    @DisplayName("add comment, then validate status code")
    public void validateStatusCode(){
        RequestUtils.addComment(comment);
        Assertions.assertEquals(201, ResponseUtils.getStatusCode());
    }

    @ParameterizedTest
    @DisplayName("add 3 comments, then validate each one")
    @CsvSource({
            "1,1 comment,1",
            "2,2 comment,2",
            "3,3 comment,3"
    })
    public void add3CommentsValidateEachOne(int id,String body,int postId){
        this.comment = new CommentDataModel(id,body,postId);
        RequestUtils.addComment(comment);
        System.out.println(RequestUtils.getResponse().extract().asPrettyString());
        Assertions.assertEquals(201,ResponseUtils.getStatusCode());
        RequestUtils.getCommentById(id);
        CommentDataModel expectedComment = ResponseUtils.jsonToObject(CommentDataModel.class);
        Assertions.assertEquals(expectedComment,comment);
    }

    @Test
    @DisplayName("add 2 comments, then validate lists size")
    public void add3CommentsValidateListSize(){
        for (int i = 1; i <= 2; i++) {
            CommentDataModel comment = Creater.createComment(i);
            RequestUtils.addComment(comment);
            System.out.println(RequestUtils.getResponse().extract().asPrettyString());
            Assertions.assertEquals(201,ResponseUtils.getStatusCode());
        }

        RequestUtils.getAllComments();
        ResponseBodyExtractionOptions responseBody = ResponseUtils.getResponse().extract().body();
        List<CommentDataModel> expectedCommentsList = responseBody.as(new TypeRef<>() {});
        Assertions.assertEquals(2,expectedCommentsList.size());

    }

//    @AfterEach
//    public void clear(){
//        System.out.println("delete Comment");
//        RequestUtils.deleteComment(comment);
//        RequestUtils.setResponse(null);
//
//    }
}
