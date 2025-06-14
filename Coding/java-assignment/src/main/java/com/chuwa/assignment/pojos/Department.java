package com.chuwa.assignment.pojos;
import  java.util.*;
public class Department {
    private String departmentName;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return departmentName;
    }

    @Override
    public boolean equals(Object o){
        if( this == o) return true;     //same object
        if(!(o instanceof Department)) return false;  // null or wrong type
        Department department = (Department) o;
        return Objects.equals(departmentName, department.departmentName);
    }
}