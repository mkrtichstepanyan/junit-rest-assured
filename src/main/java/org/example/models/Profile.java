package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.checkerframework.common.aliasing.qual.Unique;

import java.util.Objects;

public class Profile {

    @JsonProperty
    public String name;
    @JsonProperty
    public String surname;
    @JsonProperty
    public int age;

    public Profile() {
    }

    public Profile(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile profile)) return false;
        return age == profile.age && name.equals(profile.name) && surname.equals(profile.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age);
    }
}






