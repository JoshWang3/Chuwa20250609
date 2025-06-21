import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FindLongestString {
    static class Solution{
        List<String > findLongestString(List<String> list){
            return list.stream()
                    .filter(s->{
                        char[] chars = s.toCharArray();
                        char first = chars[0];
                        return first=='a'||first=='e'||first=='i'||first=='o'||first=='u';
                    })
                    .sorted()
                    .limit(3)
                    .collect(Collectors.toList());
        }
    }
    public static void main(String []args){
        String[] arrays=new String[]{"abbbb","cdddddd","eee","iou","uwuu"};
        Solution sol=new Solution();
        List<String> res=sol.findLongestString(Arrays.asList(arrays));
        System.out.println(res);
    }
}
