class Solution {
    public boolean repeatedSubstringPattern(String s) {
        // Step 1: Double the string
        String doubled = s + s;
        
        // Step 2: Remove the first and last characters
        String modified = doubled.substring(1, doubled.length() - 1);
        
        // Step 3: Check if the original string exists inside the modified string
        return modified.contains(s);
    }
}