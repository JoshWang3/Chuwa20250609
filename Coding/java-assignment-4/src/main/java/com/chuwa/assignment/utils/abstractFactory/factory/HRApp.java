package com.chuwa.assignment.utils.abstractFactory.factory;

// Client code using Abstract Factory
public class HRApp {
    private final Benefits benefits;
    private final WorkSchedule workSchedule;

    public HRApp(EmployeePolicyFactory employeePolicyFactory) {
        this.benefits = employeePolicyFactory.createBenefits();
        this.workSchedule = employeePolicyFactory.createWorkSchedule();
    }

    public void displayPolicy() {
        benefits.showBenefits();
        workSchedule.showSchedule();
    }
}

