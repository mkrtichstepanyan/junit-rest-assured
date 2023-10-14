package org.example.common;

import org.example.models.CommentDataModel;
import org.example.models.PostDataModel;

public class Creater {

    public static PostDataModel createPost(int id) {
        return new PostDataModel(id,"json-server_" + id,"typicode_" + id);
    }

    public static CommentDataModel createComment(int id) {
        return new CommentDataModel(id,id + "Comment" + id,id);
    }

}
