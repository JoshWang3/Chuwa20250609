package com.chuwa.assignment.pojos;

public class AdminUser extends User {
    private String adminLevel;
    public AdminUser(String name, String password, String email, String adminLevel) {
        super(name, password, email);   // call User (parent) constructor
        this.adminLevel = adminLevel;
    }
    public void accessAdminLevel() {
        System.out.println(name + " is accessing admin level " + adminLevel);
    }

    @Override
    public void login(){
        System.out.println("Admin " + name + " is logged in with level " + adminLevel + " accessibility");
    }
}
