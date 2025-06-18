package Coding.HW2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:
Input: nums = [1], k = 1
Output: [1]

 */

public class TopKFreqElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // minHeap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
                (a, b)  -> freqMap.get(a) - freqMap.get(b)
        );
        int[] result = new int[k];
        for (int num : freqMap.keySet()) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        TopKFreqElements topKFreqElements = new TopKFreqElements();
        int[] nums = {1,1,1,2,5,6,3,5,2};
        int k = 3;
        int[] result = topKFreqElements.topKFrequent(nums, k);
        System.out.println(Arrays.toString(result));
    }
}

// Time: O(n)
// Space: O(n) map -> n