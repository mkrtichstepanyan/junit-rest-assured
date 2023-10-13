package org.example.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.example.api.model.PostDataModel;
import static io.restassured.RestAssured.given;

public final class RequestUtils {

    private static ValidatableResponse response;


    public static ValidatableResponse getResponse() {
        return response;
    }

    public static void setResponse(ValidatableResponse response) {
        RequestUtils.response = response;
    }

    public static void getAllPosts() {
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .when()
                .get("/posts")
                .then()
                .log().ifError();
    }

    public static void getPostById() {
        getPostById(null);
    }

    public static void getPostById(Integer id) {
        String path = "/posts";
        if (id != null)
            path += "/" + id;

        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .when()
                .get(path)
                .then()
                .log().ifError();
    }

    public static void getPostWithParameter() {
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .param("id", 1)
                .when()
                .get("/posts")
                .then()
                .log().ifError();
    }

    public static void addPost() {
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"id\": 100,\n" +
                        "    \"title\": \"post 100\",\n" +
                        "    \"author\": \"type 100\"\n" +
                        "}")
                .when()
                .post("/posts")
                .then()
                .log().ifError();
    }

    public static void addPost(PostDataModel postDataModel) {
        String postDataModelJson = objectToJson(postDataModel);

        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .contentType(ContentType.JSON)
                .body(postDataModelJson)
                .when()
                .post("/posts")
                .then()
                .log().ifError();
    }

    public static void changePostByIdWithPut() {
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "      \"id\": 2,\n" +
                        "      \"title\": \"json-server_2\",\n" +
                        "      \"author\": \"typicode_222\"\n" +
                        "    }")
                .when()
                .put("/posts/2")
                .then()
                .log().ifError();
    }

    public static void changePostByIdWithPatch() {
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .contentType(ContentType.JSON)
                .body("{     \n" +
                        "      \"author\": \"typicode_2\"\n" +
                        "}")
                .when()
                .patch("/posts/2")
                .then()
                .log().ifError();
    }

    public static void deletePostById() {
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .when()
                .delete("/posts/4")
                .then()
                .log().ifError();
    }
    public static void deletePost(PostDataModel postDataModel) {

        int postId = postDataModel.getId();
        String path = "/posts" + "/" + postId;

        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .when()
                .delete(path)
                .then()
                .log().ifError();
    }

    public static void getAllComments(){
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .when()
                .get("/comments")
                .then()
                .log().ifError();
    }

    private static String objectToJson(Object o){
        String json;

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            json = ow.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return json;
    }

}
