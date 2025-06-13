package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;



public class Employee {
    private String name;
    private LocalDate dateOfBirth;
    private String department;
    private String socialSecurityNumber;
    private String homeAddress;

    // Static variable to track number of Employee instances created
    private static int employeeCount = 0;

    // Static constant
    private static final String SSN_PATTERN = "\\d{3}-\\d{2}-\\d{4}";

    // Static initialization block
    static {
        System.out.println("Static initialization block executed - Employee class loaded");
    }

    // Default constructor
    public Employee() {
        employeeCount++;
        System.out.println("Employee instance #" + employeeCount + " created");
    }

    // Parameterized constructor
    public Employee(String name, LocalDate dateOfBirth, String department,
                    String socialSecurityNumber, String homeAddress) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.socialSecurityNumber = socialSecurityNumber;
        this.homeAddress = homeAddress;
        employeeCount++;
        System.out.println("Employee instance #" + employeeCount + " created: " + name);
    }

    // Static utility method to validate SSN
    public static boolean isValidSSN(String ssn) {
        if (ssn == null || ssn.isEmpty()) {
            return false;
        }
        return ssn.matches(SSN_PATTERN);
    }

    // Static method to get employee count
    public static int getEmployeeCount() {
        return employeeCount;
    }

    // Static method to reset employee count (for demonstration)
    public static void resetEmployeeCount() {
        employeeCount = 0;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String formattedDOB = dateOfBirth != null ? dateOfBirth.format(formatter) : "N/A";

        return "Employee Information:\n" +
                "  Name: " + (name != null ? name : "N/A") + "\n" +
                "  Date of Birth: " + formattedDOB + "\n" +
                "  Department: " + (department != null ? department : "N/A") + "\n" +
                "  SSN: " + (socialSecurityNumber != null ? maskSSN(socialSecurityNumber) : "N/A") + "\n" +
                "  Home Address: " + (homeAddress != null ? homeAddress : "N/A");
    }

    // Helper method to mask SSN for security in toString
    private String maskSSN(String ssn) {
        if (ssn == null || ssn.length() < 4) {
            return "N/A";
        }
        return "XXX-XX-" + ssn.substring(ssn.length() - 4);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Employee employee = (Employee) obj;

        return Objects.equals(name, employee.name) &&
                Objects.equals(dateOfBirth, employee.dateOfBirth) &&
                Objects.equals(department, employee.department) &&
                Objects.equals(socialSecurityNumber, employee.socialSecurityNumber) &&
                Objects.equals(homeAddress, employee.homeAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth, department, socialSecurityNumber, homeAddress);
    }
}