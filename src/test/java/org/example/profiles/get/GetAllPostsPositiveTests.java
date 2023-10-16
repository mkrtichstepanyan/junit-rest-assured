package org.example.profiles.get;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.example.pojos.posts.PostsRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetAllPostsPositiveTests {

    @Test
    public void validateStatusCode() {
        RequestUtils.get("/profile");
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    public void validateResponseByJsonSchema() {
        RequestUtils.get("/profile");
        ResponseUtils.validateResponseByJsonSchema("validatorschemas/getAllProfilesValidatorSchema.json");
    }


}
