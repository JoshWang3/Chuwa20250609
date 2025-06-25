package hw2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {
    public static int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer, Integer> mapCount = new HashMap<>();
        for(int num: nums) {
            mapCount.put(num, mapCount.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        for(Map.Entry<Integer, Integer> entry: mapCount.entrySet()) {
            pq.offer(entry);
        }
        int i = 0;
        while(i < k && !pq.isEmpty()) {
            res[i++] = pq.poll().getKey();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        int[] res = topKFrequent(nums, k);
        System.out.println(Arrays.toString(res));
    }
}
