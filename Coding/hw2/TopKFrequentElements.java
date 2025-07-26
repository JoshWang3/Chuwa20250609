import java.util.*;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numFreq = new HashMap<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> (numFreq.get(b) - numFreq.get(a)));
        int[] res = new int[k];

        for (int n: nums) {
            numFreq.put(n, numFreq.getOrDefault(n, 0) + 1);
        }

        for (int key: numFreq.keySet()) {
            heap.add(key);
        }

        for (int i = 0; i < k; i++) {
            res[i] = heap.poll();
        }

        return res;
    }
}
