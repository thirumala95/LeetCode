class Solution {
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        int[][] dp = new int[maxVal + 1][maxVal + 1];
        dp[0][0] = 1; 

        for (int num : nums) {
            int[][] nextDp = new int[maxVal + 1][maxVal + 1];
            
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2] == 0) continue;

                    long count = dp[g1][g2];
                    nextDp[g1][g2] = (int) ((nextDp[g1][g2] + count) % MOD);
                    int n1 = (g1 == 0) ? num : gcd(g1, num);
                    nextDp[n1][g2] = (int) ((nextDp[n1][g2] + count) % MOD);
                    int n2 = (g2 == 0) ? num : gcd(g2, num);
                    nextDp[g1][n2] = (int) ((nextDp[g1][n2] + count) % MOD);
                }
            }
            dp = nextDp;
        }
        long totalPairs = 0;
        for (int g = 1; g <= maxVal; g++) {
            totalPairs = (totalPairs + dp[g][g]) % MOD;
        }

        return (int) totalPairs;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}