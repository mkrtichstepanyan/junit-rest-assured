package org.example.pojos.posts;

public class CommentRoot {

    private int id;
    private String body;
    private int postId;

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public int getPostId() {
        return postId;
    }

    public CommentRoot(int id, String body, int postId) {
        this.id = id;
        this.body = body;
        this.postId = postId;
    }
}