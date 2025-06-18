package com.chuwa.assignment.utils.abstractFactory.concreteProducts;


import com.chuwa.assignment.utils.abstractFactory.factory.WorkSchedule;

public class PartTimeSchedule implements WorkSchedule {
    public void showSchedule() {
        System.out.println("Part-Time Schedule: Flexible, up to 20 hrs/week");
    }
}
