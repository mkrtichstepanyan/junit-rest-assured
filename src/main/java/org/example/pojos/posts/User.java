package org.example.pojos.posts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty
    public String name;
    @JsonProperty
    public String surname;
}
