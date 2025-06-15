package org.example;

import java.util.*;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int remainSum = target - nums[i];
            if (map.containsKey(remainSum)) {
                return new int[] {map.get(remainSum), i};
            }

            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("No valid solution");
    }
}
