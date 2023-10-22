package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Profiles {
    @JsonProperty
    public String name;

    public Profiles(String name) {
        this.name = name;
    }

    public Profiles() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profiles profiles = (Profiles) o;
        return Objects.equals(name, profiles.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
