import java.util.HashMap;
import java.util.Map;

class Solution {
    public int longestPalindrome(String s) {
        // Map to store character frequencies
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        
        int length = 0;
        boolean hasOdd = false;
        
        for (int count : counts.values()) {
            // Add the largest even part of the frequency
            length += (count / 2) * 2;
            
            // Check if there is an odd remainder
            if (count % 2 != 0) {
                hasOdd = true;
            }
        }
        
        // If there was at least one odd frequency character, 
        // we can place one unique element in the middle.
        return hasOdd ? length + 1 : length;
    }
}