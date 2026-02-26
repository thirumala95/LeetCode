class Solution {
    static final long MOD = 1000000007;

    public int countGoodNumbers(long n) {

        long evenCount = (n + 1) / 2;  // positions 0,2,4...
        long oddCount = n / 2;        // positions 1,3,5...

        long part1 = power(5, evenCount);
        long part2 = power(4, oddCount);

        return (int)((part1 * part2) % MOD);
    }

    private long power(long base, long exp) {
        long result = 1;

        while (exp > 0) {
            if (exp % 2 == 1)
                result = (result * base) % MOD;

            base = (base * base) % MOD;
            exp = exp / 2;
        }

        return result;
    }
}