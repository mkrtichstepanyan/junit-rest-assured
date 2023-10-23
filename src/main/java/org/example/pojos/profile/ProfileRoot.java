package org.example.pojos.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.checkerframework.common.aliasing.qual.Unique;

public class ProfileRoot {

    @JsonProperty
    public String name;
    @JsonProperty
    public String surname;
    @JsonProperty
    public int age;
}
