package hw2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
       Map<Integer, Integer> map = new HashMap<>();
       int n = nums.length;
       for (int i = 0; i < n; i++) {
           if (map.containsKey(target - nums[i])) {
               return new int[]{map.get(target - nums[i]), i};
           }
           map.put(nums[i], i);
       }
       return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;
        int[] res = twoSum(nums, target);
        System.out.println(Arrays.toString(res));

        int[] nums2 = {3,2,4};
        int target2 = 6;
        int[] res2 = twoSum(nums2, target2);
        System.out.println(Arrays.toString(res2));
    }
}
