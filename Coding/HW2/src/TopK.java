import java.util.*;

public class TopK {
    static class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            HashMap<Integer,Integer> mp=new HashMap<>();
            for(int i=0;i<nums.length;i++){
                mp.put(nums[i],mp.getOrDefault(nums[i],0)+1);
            }
            ArrayList<Map.Entry<Integer,Integer>> list=new ArrayList<>(mp.entrySet());
            list.sort(Map.Entry.comparingByValue());
            int[] res=new int[k];
            for(int i=0;i<k;i++){
                res[i]=list.get(list.size()-1-i).getKey();
            }
            return res;
        }
    }
    public static void main(String[] args) {
        int[] nums={1,1,1,2,2,3};
        int k=2;
        System.out.println(Arrays.toString(new Solution().topKFrequent(nums, k)));
    }
}
