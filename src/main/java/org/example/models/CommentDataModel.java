package org.example.models;

public class CommentDataModel {
        private int id;
        private String body;
        private int postId;

    public CommentDataModel(int id, String body, int postId) {
        this.id = id;
        this.body = body;
        this.postId = postId;
    }

    public CommentDataModel() {
    }

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public int getPostId() {
        return postId;
    }
}
