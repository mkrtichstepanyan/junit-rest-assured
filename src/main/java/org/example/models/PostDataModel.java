package org.example.models;

import java.util.Objects;

public class PostDataModel {

    private  Integer id;
    private  String title;
    private  String author;

    public PostDataModel() {

    }

    public PostDataModel(Integer id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
    public String getAuthor() {
        return author;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostDataModel that)) return false;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(title, that.title)) return false;
        return Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
