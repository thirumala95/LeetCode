import java.util.*;

class Solution {
    public int findLHS(int[] nums) {
        
        HashMap<Integer, Integer> map = new HashMap<>();

        // Count frequencies
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int maxLen = 0;

        // Check consecutive values
        for (int key : map.keySet()) {
            if (map.containsKey(key + 1)) {
                maxLen = Math.max(
                    maxLen,
                    map.get(key) + map.get(key + 1)
                );
            }
        }

        return maxLen;
    }
}