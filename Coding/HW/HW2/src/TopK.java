import java.util.*;
import java.util.PriorityQueue;

public class TopK {
    public static int[] topKFrequent(int[] arr, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((n1, n2) -> freq.get(n1) - freq.get(n2));

        for (int key : freq.keySet()) {
            pq.add(key);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] res = new int[k];
        int i = 0;
        while (i < k) {
            res[i] = pq.poll();
            i += 1;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
    }
}