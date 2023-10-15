package org.example.pojos.comments;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentsRoot {
    @JsonProperty
    public int id;
    @JsonProperty
    public String body;
    @JsonProperty
    public int postId;
}
