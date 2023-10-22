package org.example.dataProviders.requestSpecifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationProvider {
    public static RequestSpecification requestSpecification;

    public static RequestSpecification getRequestSpecificationByRequestMethod(String requestMethod) {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost:3000")
                .build();

        switch (requestMethod) {
            case "GET", "DELETE":
                requestSpecification.contentType(ContentType.ANY);
                break;
            case "POST", "PUT", "PATCH":
                requestSpecification.contentType(ContentType.JSON);
                break;
        }
        return requestSpecification;
    }

}
