import java.util.*;

class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        
        List<List<Integer>> ans = new ArrayList<>();
        int start = 0;

        for (int i = 1; i <= s.length(); i++) {

            if (i == s.length() || s.charAt(i) != s.charAt(start)) {
                
                if (i - start >= 3) {
                    ans.add(Arrays.asList(start, i - 1));
                }

                start = i;
            }
        }

        return ans;
    }
}