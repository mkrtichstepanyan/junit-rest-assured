package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Profile {
    @JsonProperty
    public String name;

    public Profile(String name) {
        this.name = name;
    }

    public Profile() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return name.equals(profile.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}