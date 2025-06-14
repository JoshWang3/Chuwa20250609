import java.util.*;

public class Leetcode {

    public static int[] twoSum (int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int k = target - nums[i];
            int j = map.getOrDefault(k, -1);
            if (j >= 0 && i != j) {
                return new int[]{j, i};
            }
        }
        return null;
    }

    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>( Comparator.comparing(Map.Entry<Integer, Integer>::getValue).reversed());

        heap.addAll(map.entrySet());
       int[] res = new int[k];

       for (int i = 0; i < k; i++) {
           res[i] = Objects.requireNonNull(heap.poll()).getKey();
       }

       return res;



    }


}
