import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        // An IP address has 4 segments of max 3 digits each = 12 digits max
        if (s.length() < 4 || s.length() > 12) {
            return result;
        }
        
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrack(String s, int start, List<String> current, List<String> result) {
        // Base Case: If we have 4 segments
        if (current.size() == 4) {
            // If we also reached the end of the string, it's a valid IP
            if (start == s.length()) {
                result.add(String.join(".", current));
            }
            return;
        }
        
        // Try segments of length 1, 2, and 3
        for (int len = 1; len <= 3; len++) {
            // Check if we are going out of bounds
            if (start + len > s.length()) break;
            
            String segment = s.substring(start, start + len);
            
            // Validate leading zeros: length > 1 and starts with '0' is invalid
            if (segment.length() > 1 && segment.charAt(0) == '0') continue;
            
            // Validate value range: must be <= 255
            int value = Integer.parseInt(segment);
            if (value > 255) continue;
            
            // Choose
            current.add(segment);
            
            // Explore
            backtrack(s, start + len, current, result);
            
            // Un-choose (Backtrack)
            current.remove(current.size() - 1);
        }
    }
}