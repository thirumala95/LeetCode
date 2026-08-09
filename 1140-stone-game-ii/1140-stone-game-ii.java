class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        if (n == 0) return 0;

        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        int[][] memo = new int[n][n];

        return dfs(0, 1, suffixSum, memo);
    }

    private int dfs(int i, int M, int[] suffixSum, int[][] memo) {
        
        if (i + 2 * M >= suffixSum.length) {
            return suffixSum[i];
        }

        
        if (memo[i][M] != 0) {
            return memo[i][M];
        }

        int maxStones = 0;
  
        int totalRemaining = suffixSum[i];

        
        for (int X = 1; X <= 2 * M; X++) {
           
            int opponentStones = dfs(i + X, Math.max(M, X), suffixSum, memo);
            
            maxStones = Math.max(maxStones, totalRemaining - opponentStones);
        }

       
        return memo[i][M] = maxStones;
    }
}