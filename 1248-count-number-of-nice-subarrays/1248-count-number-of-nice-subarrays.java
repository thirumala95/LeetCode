class Solution {

    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {

        if (k < 0) return 0;

        int l = 0, sum = 0, count = 0;

        for (int r = 0; r < nums.length; r++) {

            if (nums[r] % 2 == 1) {
                sum++;
            }

            while (sum > k) {
                if (nums[l] % 2 == 1) {
                    sum--;
                }
                l++;
            }

            count += (r - l + 1);
        }

        return count;
    }
}