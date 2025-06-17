package Encapsulation;

import java.util.Date;

public class Person {
    private String name;

    private int age;

    private Date dateOfBirth;

    private String profession;

    public Person(String name, int age, Date dateOfBirth, String profession) {
        this.name = name;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.profession = profession;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getProfession() {
        return profession;
    }
}
