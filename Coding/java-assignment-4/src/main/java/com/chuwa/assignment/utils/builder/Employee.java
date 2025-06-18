package com.chuwa.assignment.utils.builder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

// Immutable Employee constructed via Builder
public class Employee {
    private final long id;
    private final String name;
    private final String title;
    private final LocalDate hireDate;
    private final double salary;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd, yyyy");

    private Employee(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.title = builder.title;
        this.hireDate = builder.hireDate;
        this.salary = builder.salary;
        }

    @Override
    public String toString() {
        return String.format("%d: %s (%s) hired %s, salary %.2f", id, name, title,  hireDate.format(DATE_FORMAT), salary);
    }
    // Nested Builder
    public static class Builder {
        private final long id;
        private final String name;

        private String title = "Staff";
        private LocalDate hireDate = LocalDate.now();
        private double salary = 60_000.00;

        public Builder(long id, String name) {
            this.id = id;
            this.name = Objects.requireNonNull(name);
        }

        public Builder title(String title) { this.title = title; return this; }
        public Builder hireDate(LocalDate hireDate) { this.hireDate = hireDate; return this; }
        public Builder salary(double salary) { this.salary = salary; return this; }

        public Employee build() {
            return new Employee(this);
        }
    }
}
