package org.example.posts.get;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataProviders.requestSpecifications.RequestSpecificationProvider;
import org.example.pojos.posts.PostsRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllPostsPositiveTests {
    public String endpoint = "/posts/";
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @Test
    public void validateStatusCode() {

        RequestUtils.get(getSpecs, endpoint);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    public void validateResponseByJsonSchema() {
        RequestUtils.get(getSpecs, endpoint);
        ResponseUtils.validateResponseByJsonSchema("validatorschemas/getAllPostsValidatorSchema.json");
    }


    @Test
    public void validateGetPostById() {
        RequestUtils.get(getSpecs, endpoint, 1);

        PostsRoot objectByJsonString = ResponseUtils.getObjectByJsonString(PostsRoot.class);

        ResponseUtils.getStringValueByJsonPath("id");
        ResponseUtils.getStringValueByJsonPath("user.name");
    }

    @Test
    public void validateGetPostByParam() throws IOException {

        List<HashMap<String, Object>> params = RequestUtils.readJsonFile("src/main/java/org/example/dataProviders/PostParamsSet.json");

        for (HashMap<String, Object> param : params) {
            RequestUtils.get(getSpecs, endpoint, param);

            List<Map<String, Object>> posts = ResponseUtils.getObjectByJsonString(List.class);

            for (Map<String, Object> post : posts) {
                for (Map.Entry<String, Object> entry : param.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    Object expectedValue = post.get(key);
                    Assertions.assertEquals(expectedValue, value);
                }
            }
        }
    }
}
