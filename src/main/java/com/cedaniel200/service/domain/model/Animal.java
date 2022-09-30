package com.cedaniel200.service.domain.model;

public class Animal {

    private final String type;
    private final String name;

    public Animal(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
