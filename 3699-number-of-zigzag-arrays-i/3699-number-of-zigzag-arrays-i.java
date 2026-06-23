class Solution {
    public int zigZagArrays(int n, int l, int r) {
        long MOD = 1000000007;
        int M = r - l + 1;
        
        // dp[0][x]: next move must go down (prev move went up)
        // dp[1][x]: next move must go up (prev move went down)
        long[] dp0 = new long[M];
        long[] dp1 = new long[M];
        
        // Base case: for length 2, any pair (x, y) with x != y is valid
        // If x < y (upward move), next move must be down -> contributes to dp0[y]
        // If x > y (downward move), next move must be up -> contributes to dp1[y]
        for (int y = 0; y < M; y++) {
            dp0[y] = y;         // there are y elements strictly smaller than y
            dp1[y] = M - 1 - y; // there are M - 1 - y elements strictly greater than y
        }
        
        // Process from length 3 up to n
        for (int i = 3; i <= n; i++) {
            long[] nextDp0 = new long[M];
            long[] nextDp1 = new long[M];
            
            // 1. Optimize nextDp0[y] = sum(dp1[x]) for x < y using a rolling prefix sum
            long prefixSum = 0;
            for (int y = 0; y < M; y++) {
                nextDp0[y] = prefixSum;
                prefixSum = (prefixSum + dp1[y]) % MOD;
            }
            
            // 2. Optimize nextDp1[y] = sum(dp0[x]) for x > y using a rolling suffix sum
            long suffixSum = 0;
            for (int y = M - 1; y >= 0; y--) {
                nextDp1[y] = suffixSum;
                suffixSum = (suffixSum + dp0[y]) % MOD;
            }
            
            dp0 = nextDp0;
            dp1 = nextDp1;
        }
        
        // Total valid arrays of length n is the sum of all terminal states
        long totalCount = 0;
        for (int x = 0; x < M; x++) {
            totalCount = (totalCount + dp0[x] + dp1[x]) % MOD;
        }
        
        return (int) totalCount;
    }
}