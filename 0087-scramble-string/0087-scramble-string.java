import java.util.HashMap;
import java.util.Map;

public class Solution {
    // Memoization map to store results of evaluated string pairs
    private Map<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        // Base Case: If strings are identical
        if (s1.equals(s2)) {
            return true;
        }
        
        // Base Case: If lengths differ, they can't be scrambled versions
        if (s1.length() != s2.length()) {
            return false;
        }

        // Check if we have already computed this pair
        String key = s1 + "_" + s2;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Pruning: Check if both strings have the exact same character frequencies
        int[] count = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for (int c : count) {
            if (c != 0) {
                memo.put(key, false);
                return false;
            }
        }

        int n = s1.length();
        // Try splitting the string at every possible index `i`
        for (int i = 1; i < n; i++) {
            // Case 1: Without swapping the two parts
            // s1 left vs s2 left AND s1 right vs s2 right
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && 
                isScramble(s1.substring(i), s2.substring(i))) {
                memo.put(key, true);
                return true;
            }

            // Case 2: With swapping the two parts
            // s1 left vs s2 right AND s1 right vs s2 left
            if (isScramble(s1.substring(0, i), s2.substring(n - i)) && 
                isScramble(s1.substring(i), s2.substring(0, n - i))) {
                memo.put(key, true);
                return true;
            }
        }

        memo.put(key, false);
        return false;
    }
}