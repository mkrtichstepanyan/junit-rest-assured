package org.example.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.example.models.PostDataModel;
import static io.restassured.RestAssured.given;

public final class RequestUtils {

    private static ValidatableResponse response;

    public static ValidatableResponse getResponse() {
        return response;
    }

    static {
        RestAssured.baseURI = "http://localhost:3000";
    }

    public static void setResponse(ValidatableResponse response) {
        RequestUtils.response = response;
    }

    public static void getAllPosts() {
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

        response = given()
                .when()
                .get(path)
                .then()
                .log().ifError();
    }

    public static void getPostWithParameter() {

        response = given()
                .param("id", 1)
                .when()
                .get("/posts")
                .then()
                .log().ifError();
    }

    public static void addPost() {
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

        response = given()
                .contentType(ContentType.JSON)
                .body(postDataModelJson)
                .when()
                .post("/posts")
                .then()
                .log().ifError();
    }

    public static void changePostByIdWithPut() {
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
        response = given()
                .when()
                .delete("/posts/4")
                .then()
                .log().ifError();
    }
    public static void deletePost(PostDataModel postDataModel) {

        int postId = postDataModel.getId();
        String path = "/posts" + "/" + postId;

        response = given()
                .when()
                .delete(path)
                .then()
                .log().ifError();
    }

    public static void getAllComments() {
        response = given()
                .when()
                .get("/comments")
                .then()
                .log().ifError();
    }

    public static void getCommentById(int id){
        String path = "/comments" + "/" + id;
        response = given()
                .when()
                .get(path)
                .then()
                .log().ifError();
    }

    public static String objectToJson(Object o){
        String jsonString;
        ObjectMapper om = new ObjectMapper();
        try {
            jsonString = om.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return jsonString;
    }
}
