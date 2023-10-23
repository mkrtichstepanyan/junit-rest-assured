package org.example.posts.post.negativerequests;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.example.models.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CreateNowPostsNegativeTests {

    RequestSpecification postSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("POST");
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    public void validateCreateNewPostWithWrongPath() {
        Post post = new Post(7, "Title 7", "Author 7");
        String jsonStringByObject = RequestUtils.getJsonStringByObject(post);
        RequestUtils.post(postSpec, "/post", jsonStringByObject);
        int resultByStatusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, resultByStatusCode);
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "ggg, 7845, 9888",
                    "455, 487859, 78956"
            }
    )
    public void validateCreateNewPostWithWrongTypeOfValue(int id, String title, String author) {
        Post expectedPost = new Post(id, title, author);
        String jsonStringBody = RequestUtils.getJsonStringByObject(expectedPost);
        RequestUtils.post(postSpec, "/posts/", jsonStringBody);
        int resultByStatusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, resultByStatusCode);

        RequestUtils.get(getSpec, "/posts/", id);
        Post actualPost = ResponseUtils.getObjectByJsonString(Post.class);
        Assertions.assertEquals(expectedPost, actualPost);
    }


}
