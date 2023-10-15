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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Comment comment = (Comment) obj;
        return id == comment.id && body.equals(comment.body) && postId == comment.postId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, postId);
    }
}
