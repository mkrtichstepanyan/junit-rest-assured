package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Post {
    @JsonProperty
    public int id;
    @JsonProperty
    public String title;
    @JsonProperty
    public String author;

    public Post(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Post() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post post)) return false;
        return id == post.id && title.equals(post.title) && author.equals(post.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author);
    }
}
