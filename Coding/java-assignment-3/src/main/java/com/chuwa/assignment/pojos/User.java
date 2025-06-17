package com.chuwa.assignment.pojos;

public class User {
    // Need to set protected in order to let subclass inherit
    protected String name;
    protected String password;
    protected String email;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void login() {
        System.out.println(name + " has logged in.");
    }
}
