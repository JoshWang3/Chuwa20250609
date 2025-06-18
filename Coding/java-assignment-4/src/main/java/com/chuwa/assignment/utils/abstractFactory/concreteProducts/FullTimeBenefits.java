package com.chuwa.assignment.utils.abstractFactory.concreteProducts;

import com.chuwa.assignment.utils.abstractFactory.factory.Benefits;

// Concrete Products - FullTime vs PartTime
public class FullTimeBenefits implements Benefits {
    public void showBenefits() {
        System.out.println("Full Time Benefits: Health, Dental, 401k");
    }
}
