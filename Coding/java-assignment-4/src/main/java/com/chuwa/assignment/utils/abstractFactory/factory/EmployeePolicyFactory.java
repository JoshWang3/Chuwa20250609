package com.chuwa.assignment.utils.abstractFactory.factory;

// Abstract Factory Interface
public interface EmployeePolicyFactory {
    Benefits createBenefits();
    WorkSchedule createWorkSchedule();
}
