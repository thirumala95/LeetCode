class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int powerOfTwo = 1;
        while (powerOfTwo <= n) {
            powerOfTwo <<= 1;
        }

        return powerOfTwo;
    }
}