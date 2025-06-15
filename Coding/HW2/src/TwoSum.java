import java.util.*;
public class TwoSum {
    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            HashMap<Integer,Integer> mp= new HashMap<>();
            for(int i=0;i<nums.length;i++){
                if(mp.containsKey(target-nums[i])){
                    return new int[]{mp.get(target-nums[i]),i};
                }
                else{
                    mp.put(nums[i],i);
                }
            }
            return new int[0];
        }
    }
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.twoSum(nums, target)));
    }
}
