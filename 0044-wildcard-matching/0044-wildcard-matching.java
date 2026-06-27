class Solution {
    public boolean isMatch(String s, String p) {
        int sIdx = 0, pIdx = 0;
        int matchIdx = 0, starIdx = -1;            
        
        while (sIdx < s.length()) {
            // 1. If characters match or pattern has '?'
            if (pIdx < p.length() && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                sIdx++;
                pIdx++;
            } 
            // 2. If pattern has '*', record the position and assume it matches 0 characters first
            else if (pIdx < p.length() && p.charAt(pIdx) == '*') {
                starIdx = pIdx;
                matchIdx = sIdx;
                pIdx++;
            } 
            // 3. If there was a previous '*', backtrack: advance match string index, reset pattern index
            else if (starIdx != -1) {
                pIdx = starIdx + 1;
                matchIdx++;
                sIdx = matchIdx;
            } 
            // 4. Characters don't match and no '*' to backtrack to
            else {
                return false;
            }
        }
        
        // Check for remaining characters in pattern (only '*' can match empty remaining string)
        while (pIdx < p.length() && p.charAt(pIdx) == '*') {
            pIdx++;
        }
        
        return pIdx == p.length();
    }
}