package com.chuwa.assignment.utils.abstractFactory;

import com.chuwa.assignment.utils.abstractFactory.factory.FullTimePolicyFactory;
import com.chuwa.assignment.utils.abstractFactory.factory.HRApp;
import com.chuwa.assignment.utils.abstractFactory.factory.PartTimePolicyFactory;

public class AbstractFactory {
    public static void main(String[] args) {
        System.out.println("\n=== Abstract Factory Demo: Employee HR System ===\n");

        HRApp fullTimeEmployee = new HRApp(new FullTimePolicyFactory());
        HRApp partTimeEmployee = new HRApp(new PartTimePolicyFactory());

        System.out.println("Full-Time Employee Policies -");
        fullTimeEmployee.displayPolicy();

        System.out.println("Part-Time Employee Policies -");
        partTimeEmployee.displayPolicy();
    }
}

// Explanation
//
// This abstract factory creates consistent combinations of policy
// components for different employee types.
// Benefits + Schedule are produced together by the same factory.
//
// Benefits:
// - Groups related objects for better consistency
// - Easily extendable: add contractors or interns with new factories
// - Keep client (HRApp) decoupled from implementation details

