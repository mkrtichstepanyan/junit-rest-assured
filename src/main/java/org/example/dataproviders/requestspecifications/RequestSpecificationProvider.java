package org.example.dataproviders.requestspecifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationProvider {

    public static RequestSpecification requestSpecification;


    public static RequestSpecification getRequestSpecificationByRequestMethod(String requestMethod) {

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost:3000")
              //  .addHeader("Authorization", "Bearer asdasdawdasd" )
                .build();

        switch (requestMethod) {
            case "GET":
                requestSpecification.contentType(ContentType.JSON);
                break;
            case "POST":
                requestSpecification.contentType(ContentType.JSON);
                break;
            case "PUT":
                requestSpecification.contentType(ContentType.JSON);
                break;
            default:
                requestSpecification.contentType(ContentType.JSON);
        }
        return requestSpecification;
    }
}
