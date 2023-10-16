package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Comments {
    @JsonProperty
    public int id;
    @JsonProperty
    public String body;
    @JsonProperty
    public int postId;

    public Comments(int id, String body, int postId) {
        this.id = id;
        this.body = body;
        this.postId = postId;
    }

    public Comments() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments = (Comments) o;
        return id == comments.id && body.equals(comments.body) && postId == comments.postId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, postId);
    }
}