class Solution {
    public int maxScore(int[] cardPoints, int k) {

        int n = cardPoints.length;

        int total = 0;
        for (int x : cardPoints) total += x;

        int windowSize = n - k;

        if (windowSize == 0) return total;

        int sum = 0;

        // first window
        for (int i = 0; i < windowSize; i++) {
            sum += cardPoints[i];
        }

        int minSum = sum;

        // sliding window
        for (int i = windowSize; i < n; i++) {
            sum += cardPoints[i] - cardPoints[i - windowSize];
            minSum = Math.min(minSum, sum);
        }

        return total - minSum;
    }
}