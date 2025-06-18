package com.chuwa.assignment.utils.abstractFactory.concreteProducts;

import com.chuwa.assignment.utils.abstractFactory.factory.WorkSchedule;

public class FullTimeSchedule implements WorkSchedule {
    public void showSchedule() {
        System.out.println("Full-Time Schedule: Mon–Fri, 9am–5pm");
    }
}
