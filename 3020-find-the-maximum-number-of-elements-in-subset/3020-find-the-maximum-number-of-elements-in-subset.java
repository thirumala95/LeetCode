import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put((long) num, countMap.getOrDefault((long) num, 0) + 1);
        }

        // Handle the special case for 1s
        int maxLen = 1; 
        if (countMap.containsKey(1L)) {
            int onesCount = countMap.get(1L);
            maxLen = (onesCount % 2 == 0) ? onesCount - 1 : onesCount;
        }

        // Check for sequences starting with x > 1
        for (long x : countMap.keySet()) {
            if (x == 1) continue;

            int currentLen = 0;
            long curr = x;

            while (countMap.containsKey(curr)) {
                long nextSquare = curr * curr;
                
                // If we have at least 2 of 'curr' AND the next square exists in the map
                if (countMap.get(curr) >= 2 && countMap.containsKey(nextSquare)) {
                    currentLen += 2;
                    curr = nextSquare;
                } else {
                    // Otherwise, 'curr' must act as the peak element of this subset sequence
                    currentLen += 1;
                    break;
                }
            }

            maxLen = Math.max(maxLen, currentLen);
        }

        return maxLen;
    }
}