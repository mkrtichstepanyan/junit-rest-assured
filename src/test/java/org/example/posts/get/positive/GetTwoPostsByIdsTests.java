package org.example.posts.get.positive;

import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetTwoPostsByIdsTests {
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");
    public static final String TWO_IDS_FILE = "/csvfiles/two_ids.csv";
    public static final String ENDPOINT = "/posts";

    @ParameterizedTest
    @CsvFileSource(resources = TWO_IDS_FILE)
    public void validateGettingTwoPosts(int id1, int id2) {
        RequestUtils.get(getSpec, ENDPOINT, "id", id1, id2);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
    }

}
