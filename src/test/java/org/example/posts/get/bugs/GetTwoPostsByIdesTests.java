package org.example.posts.get.bugs;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class GetTwoPostsByIdesTests {

    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");

    @ParameterizedTest
    @CsvFileSource(resources = "/csvfiles/twoNonExistingIds.csv")
    public void validateGetPostsByNonExistingIdes(int id1, int id2) {
        RequestUtils.get(getSpec, "/posts/", "id", id1, id2);
        int resultByStatusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, resultByStatusCode);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvfiles/existingAndNonExistingIdes.csv")
    public void validateGetPostsByExistingAndNonExistingIdes(int id1, int id2) {
        RequestUtils.get(getSpec, "/posts/", "id", id1, id2);
        int resultStatusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, resultStatusCode);

    }

}
