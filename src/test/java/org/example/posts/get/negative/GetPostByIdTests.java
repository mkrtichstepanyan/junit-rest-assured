package org.example.posts.get.negative;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class GetPostByIdTests {
    RequestSpecification getSpecs = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");
    public static final String ENDPOINT = "/posts";
    public static final String PATH_TO_INVALID_IDS = "/csvfiles/valid_ids.csv";

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_INVALID_IDS)
    public void validateGetPostById(int id) {
        RequestUtils.get(getSpecs, ENDPOINT, id);

//        PostsRoot objectByJsonString = ResponseUtils.getObjectByJsonString(PostsRoot.class);
//
//        ResponseUtils.getStringValueByJsonPath("id");
//        ResponseUtils.getStringValueByJsonPath("user.name");
    }
}
