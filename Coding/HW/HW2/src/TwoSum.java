import java.util.*;

public class TwoSum {

    public static int[] calculate(int[] nums, int target) {
        Map<Integer, Integer> memo = new HashMap<>();

        for (int i = 0; i < nums.length; i += 1) {
            if (memo.containsKey(target - nums[i])) {
                return new int[]{memo.get(target - nums[i]), i};
            }
            else {
                memo.put(nums[i], i);
            }
        }

        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(calculate(nums, target)));
    }
}