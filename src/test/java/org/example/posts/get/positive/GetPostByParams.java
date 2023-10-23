package org.example.posts.get.positive;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.example.pojos.posts.PostsRoot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;

public class GetPostByParams {
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");
    public static final String ENDPOINT = "/posts";
    public static final String PATH_TO_POST_VALID_VALUES = "/csvfiles/post_valid_values.csv";

    @ParameterizedTest
    @MethodSource("mapCreator")
    public void validateGetPostByParams(Map<String, Object> params) {
        RequestUtils.get(getSpecs, ENDPOINT, params);

        PostsRoot objectByJsonString = ResponseUtils.getObjectByJsonString(PostsRoot.class);

        String id = ResponseUtils.getStringValueByJsonPath("id");
        String stringValueByJsonPath = ResponseUtils.getStringValueByJsonPath("user.name");

    }


    @CsvFileSource(resources = PATH_TO_POST_VALID_VALUES)
   private static Map<String, Object> mapCreator(String title, String author){
       Map<String, Object> params = new HashMap<>();
       params.put("title", title);
       params.put("author", author);
       return params;
   }
}
