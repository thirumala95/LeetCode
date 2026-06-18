class Solution {
    public char findTheDifference(String s, String t) {
        char result = 0;
        
        // XOR all characters from string s
        for (int i = 0; i < s.length(); i++) {
            result ^= s.charAt(i);
        }
        
        // XOR all characters from string t
        for (int i = 0; i < t.length(); i++) {
            result ^= t.charAt(i);
        }
        
        return result;
    }
}