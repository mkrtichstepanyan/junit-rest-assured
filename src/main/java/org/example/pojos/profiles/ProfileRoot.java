package org.example.pojos.profiles;

import com.fasterxml.jackson.annotation.JsonProperty;
import groovy.transform.AutoImplement;
import org.checkerframework.common.aliasing.qual.Unique;

public class ProfileRoot {

    @JsonProperty
    public String name;

    @JsonProperty
    public String surname;

    @JsonProperty
    public int age;


}
