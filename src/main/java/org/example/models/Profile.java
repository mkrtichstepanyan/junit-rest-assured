package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Profile {
    @JsonProperty
    public String name;

    @JsonProperty
    public int number;

    public Profile(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public Profile() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile profile)) return false;
        return number == profile.number && name.equals(profile.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number);
    }
}
