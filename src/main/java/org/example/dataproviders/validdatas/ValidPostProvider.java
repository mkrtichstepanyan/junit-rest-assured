package org.example.dataproviders.validdatas;

import org.example.models.Post;

public class ValidPostProvider {

    public static Post createValidPost (int id, String title, String author){
        return new Post(id, title, author);
    }
}
