class Solution {
    public boolean isSubsequence(String s, String t) {
        int p1 = 0; // Pointer for s
        int p2 = 0; // Pointer for t
        
        // Scan through both strings
        while (p1 < s.length() && p2 < t.length()) {
            // If characters match, move the s pointer forward
            if (s.charAt(p1) == t.charAt(p2)) {
                p1++;
            }
            // Always move the t pointer forward
            p2++;
        }
        
        // If p1 matched all characters of s, it will equal s.length()
        return p1 == s.length();
    }
}