class Solution {
    public int minCut(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        
        int n = s.length();
        
        // isPal[i][j] will be true if substring s[i...j] is a palindrome
        boolean[][] isPal = new boolean[n][n];
        
        // Fill the palindrome table
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // It's a palindrome if the length is <= 2 or the inner substring is a palindrome
                    if (j - i <= 2 || isPal[i + 1][j - 1]) {
                        isPal[i][j] = true;
                    }
                }
            }
        }
        
        // cuts[i] stores the minimum cuts needed for substring s[0...i]
        int[] cuts = new int[n];
        
        for (int i = 0; i < n; i++) {
            // Maximum possible cuts for s[0...i] is i cuts (cutting after every character)
            if (isPal[0][i]) {
                cuts[i] = 0; // Entire substring s[0...i] is already a palindrome
            } else {
                int minCuts = i;
                for (int j = 0; j < i; j++) {
                    // If s[j+1...i] is a palindrome, we can cut after index j
                    if (isPal[j + 1][i]) {
                        minCuts = Math.min(minCuts, cuts[j] + 1);
                    }
                }
                cuts[i] = minCuts;
            }
        }
        
        return cuts[n - 1];
    }
}