package com.chuwa.assignment.anonymousClass;

public class AnonymousClass {
    public static void main(String[] args) {
        System.out.println("\n=== Anonymous Class===\n");
        // Anonymous class implementing a GradingPolicy interface
        GradingPolicy gradePolicy = new GradingPolicy() {
            @Override
            public String assignGrade(int score) {
                if(score >= 90) { return "A";}
                if(score >= 80) { return "B";}
                if(score >= 70) { return "C";}
                return "F";
            }
        };

        System.out.println("Student score: 85 -> Grade: " + gradePolicy.assignGrade(85));
        System.out.println("Student score: 65 -> Grade: " + gradePolicy.assignGrade(65));

        // Anonymous class extending SchoolRole for a Principal
        SchoolRole principal = new SchoolRole("Principal Lee") {
            @Override
            public void describe() {
                System.out.println(getName() + ": Oversees school operations and staff.");
            }
        };

        principal.describe();
    }
}
//  • Anonymous classes let us write small one-time logic for business or academic domains,
//    like policy and roles.
//  • Great for scenario where object behavior is customized inside the method.