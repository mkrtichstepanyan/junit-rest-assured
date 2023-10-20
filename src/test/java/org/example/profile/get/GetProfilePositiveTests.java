package org.example.profile.get;

import org.example.api.RequestUtils;
import org.example.api.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GetProfilePositiveTests {


    @Test
    @DisplayName("request getProfile(), then validate status code")
    public void validateStatusCode(){
        RequestUtils.getProfile();
        System.out.println(ResponseUtils.getResponse().extract().asPrettyString());
        Assertions.assertEquals(200,ResponseUtils.getStatusCode());
    }
}
