package Coding.hw2;

import java.util.*;

public class TopKFrequent {
    public int[] topKFrequent(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));

        for (int n : arr) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        pq.addAll(map.keySet());

        int[] ans = new int[k];
        int i = 0;
        while (i < k) {
            ans[i] = pq.poll();
            i++;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2, 2, 3};
        int k = 2;
        TopKFrequent topKFrequent = new TopKFrequent();
        System.out.println(Arrays.toString(topKFrequent.topKFrequent(arr, k)));
    }
}
