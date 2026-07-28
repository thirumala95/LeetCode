class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int half = n / 2;
        
        // Count frequencies of characters in the first half
        int[] count = new int[26];
        for (int i = 0; i < half; i++) {
            count[s.charAt(i) - 'a']++;
        }
        
        // Build the sorted left half
        StringBuilder left = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            while (count[i] > 0) {
                left.append((char) ('a' + i));
                count[i]--;
            }
        }
      
        String mid = (n % 2 == 1) ? String.valueOf(s.charAt(half)) : "";
        
        
        
        StringBuilder right = new StringBuilder(left).reverse();
        return left.toString() + mid + right.toString();
    }
}