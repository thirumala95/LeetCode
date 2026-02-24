import java.util.*;

class Solution {
    public int maxFrequencyElements(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Step 1: Count frequency
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Step 2: Find maximum frequency
        int maxFreq = 0;
        for (int freq : map.values()) {
            maxFreq = Math.max(maxFreq, freq);
        }

        // Step 3: Count total elements having max frequency
        int total = 0;
        for (int freq : map.values()) {
            if (freq == maxFreq) {
                total += freq;
            }
        }

        return total;
    }
}

