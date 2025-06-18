package com.chuwa.assignment.utils.abstractFactory.factory;

import com.chuwa.assignment.utils.abstractFactory.concreteProducts.FullTimeBenefits;
import com.chuwa.assignment.utils.abstractFactory.concreteProducts.FullTimeSchedule;

// Concrete Factories - FullTime and PartTime
public class FullTimePolicyFactory implements EmployeePolicyFactory {
    public Benefits createBenefits() {
        return new FullTimeBenefits();
    }
    public WorkSchedule createWorkSchedule() {
        return new FullTimeSchedule();
    }
}
