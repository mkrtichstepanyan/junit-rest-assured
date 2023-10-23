package org.example.pojos.comments;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CommentRoot {
    @JsonProperty
    public int id;

    @JsonProperty
    public String body;

    @JsonProperty
    public int postId;
}