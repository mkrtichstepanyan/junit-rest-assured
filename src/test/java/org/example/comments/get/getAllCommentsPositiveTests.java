package org.example.comments.get;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.models.CommentsDataModel;
import org.junit.jupiter.api.*;

public class getAllCommentsPositiveTests {


    CommentsDataModel commentsDataModel;
    @AfterEach
    public void start(){
        System.out.println("after");
        commentsDataModel = createComment();
    }

    @Test
    @DisplayName("Request get all comments then validate status code is 200")
    public void validateStatusCode() {
        RequestUtils.getAllComments();
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());
        Assertions.assertEquals(200, ResponseUtils.getStatusCode());
    }

    @Test
    @DisplayName("Request get comments by id then validate id")
    public void getCommentsById() {
        int expectedResult = 1;
        RequestUtils.getCommentById(expectedResult);
        CommentsDataModel actualCommentsDataModel = ResponseUtils.jsonToObject(CommentsDataModel.class);
        System.out.println(RequestUtils.getResponse().extract().asPrettyString());
        Assertions.assertEquals(expectedResult,actualCommentsDataModel.getId());

    }

    @BeforeEach
    public void finish(){
        RequestUtils.setResponse(null);

    }

    private static CommentsDataModel createComment() {
        return new CommentsDataModel(2, "2 comment", 2);
    }
}
