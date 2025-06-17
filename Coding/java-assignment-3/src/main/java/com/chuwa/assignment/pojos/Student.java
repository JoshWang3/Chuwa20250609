package com.chuwa.assignment.pojos;

public class Student {
    private String name;
    private int id;
    private int grade;

    public Student(String name, int id, int grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        if(grade >= 0 && grade <= 100){
            this.grade = grade;
        } else {
            System.out.println("Invalid grade");
        }
    }
}