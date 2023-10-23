package org.example.posts.get.positiverequests;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class GetPostsByTwoIdesTests {
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @ParameterizedTest
    @CsvFileSource(resources = "/csvfiles/twoValidIds.csv")
    public void validateGetPostsByTwoIdes(int id1, int id2) {
        RequestUtils.get(getSpec, "/posts/", "id", id1, id2);
        int resultByStatusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, resultByStatusCode);
    }

}
