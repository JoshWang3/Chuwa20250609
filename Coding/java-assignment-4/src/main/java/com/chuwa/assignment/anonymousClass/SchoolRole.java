package com.chuwa.assignment.anonymousClass;

public abstract class SchoolRole {
    private final String name;
    public SchoolRole(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public abstract void describe();
}
