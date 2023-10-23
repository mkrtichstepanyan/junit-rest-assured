package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Comment {
    @JsonProperty
    public int id;
    @JsonProperty
    public String body;
    @JsonProperty
    public int postId;

    public Comment() {
    }

    public Comment(int id, String body, int postId) {
        this.id = id;
        this.body = body;
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;
        return id == comment.id && postId == comment.postId && body.equals(comment.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, postId);
    }
}
