package com.chuwa.assignment.anonymousClass;

// interface for grade calculation
@FunctionalInterface
public interface GradingPolicy {
    String assignGrade(int score);
}
