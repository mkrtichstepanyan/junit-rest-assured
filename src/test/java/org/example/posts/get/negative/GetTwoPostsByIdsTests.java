package org.example.posts.get.negative;

import io.restassured.specification.RequestSpecification;
import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.dataproviders.requestspecifications.RequestSpecificationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class GetTwoPostsByIdsTests {
    RequestSpecification getSpec = RequestSpecificationProvider.getRequestSpecificationByRequestMethod("GET");
    public static final String NON_EXISTING_IDS_FILE = "/csvfiles/two_non_existing_ids.csv";
    public static final String EXISTING_AND_NON_EXISTING_IDS_FILE = "/csvfiles/two_non_existing_ids.csv";
    public static final String ENDPOINT = "/posts/";

    @ParameterizedTest
    @CsvFileSource(resources = NON_EXISTING_IDS_FILE)
    public void validateGettingNonExistingTwoPosts(int id1, int id2) {
        RequestUtils.get(getSpec, ENDPOINT, "id", id1, id2);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);
    }

    @ParameterizedTest
    @CsvFileSource(resources = EXISTING_AND_NON_EXISTING_IDS_FILE)
    public void validateGettingExistingAndNonExistingPostsByIds(int id1, int id2) {
        RequestUtils.get(getSpec, ENDPOINT, "id", id1, id2);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(404, statusCode);
    }

}
