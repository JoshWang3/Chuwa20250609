package com.chuwa.assignment.utils.abstractFactory.concreteProducts;

import com.chuwa.assignment.utils.abstractFactory.factory.Benefits;

public class PartTimeBenefits implements Benefits {
    @Override
    public void showBenefits() {
        System.out.println("Part-Time Benefits: Limited Health Coverage");
    }
}
