class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // dp[j] stores the number of distinct subsequences of s that equal t[0...j-1]
        int[] dp = new int[n + 1];
        
        // Base case: an empty t can be formed by any prefix of s in exactly 1 way
        dp[0] = 1;
        
        // Process each character of s
        for (int i = 1; i <= m; i++) {
            // Iterate backwards through t to use the values from the previous row
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] = dp[j] + dp[j - 1];
                }
                // If they don't match, dp[j] remains unchanged (dp[j] = dp[j])
            }
        }
        
        return dp[n];
    }
}