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

    public Comment(int id, String body, int postId) {
        this.id = id;
        this.body = body;
        this.postId = postId;
    }

    public Comment() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comments = (Comment) o;
        return id == comments.id && body.equals(comments.body) && postId == comments.postId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, postId);
    }
}