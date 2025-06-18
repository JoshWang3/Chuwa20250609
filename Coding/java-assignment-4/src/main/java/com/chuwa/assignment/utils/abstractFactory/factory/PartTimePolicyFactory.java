package com.chuwa.assignment.utils.abstractFactory.factory;

import com.chuwa.assignment.utils.abstractFactory.concreteProducts.PartTimeBenefits;
import com.chuwa.assignment.utils.abstractFactory.concreteProducts.PartTimeSchedule;

public class PartTimePolicyFactory implements EmployeePolicyFactory {
    public Benefits createBenefits() {
        return new PartTimeBenefits();
    }
    public WorkSchedule createWorkSchedule() {
        return new PartTimeSchedule();
    }
}
