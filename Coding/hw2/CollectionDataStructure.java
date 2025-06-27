import java.util.*;

public static void main(String[] args) {

}

public int[] TopKFrequentElements(int[] arr, int k) {
    Map<Integer, Integer> frequencyMap = new HashMap<>();
    for (int num : arr) {
        frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
    }

    PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(k, Map.Entry.comparingByValue());
    for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
        minHeap.offer(entry);
        if (minHeap.size() > k) {
            minHeap.poll();
        }
    }

    int[] result = new int[k];
    for (int i = 0; i < k; i++) {
        result[i] = minHeap.poll().getKey();
    }
    return result;
}

public int[] TwoSum(int[] nums, int target) {
    Map<Integer, Integer> numToIndex = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (numToIndex.containsKey(complement)) {
            return new int[]{numToIndex.get(complement), i};
        }
        numToIndex.put(nums[i], i);
    }
    return new int[0]; // Return an empty array if no solution is found
}