package org.example.models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentDataModel that)) return false;

        if (id != that.id) return false;
        if (postId != that.postId) return false;
        return Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + postId;
        return result;
    }
}
