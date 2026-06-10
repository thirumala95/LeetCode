class Solution {

    public int splitArray(int[] nums, int k) {

        int low = 0, high = 0;

        for (int num : nums) {
            low = Math.max(low, num);
            high += num;
        }

        int ans = high;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (canSplit(nums, k, mid)) {
                ans = mid;
                high = mid - 1; // try smaller maximum
            } else {
                low = mid + 1;  // need bigger limit
            }
        }

        return ans;
    }

    // Greedy check: number of subarrays needed
    private boolean canSplit(int[] nums, int k, int limit) {
        int count = 1;
        int sum = 0;

        for (int num : nums) {

            if (sum + num <= limit) {
                sum += num;
            } else {
                count++;
                sum = num;

                if (count > k) return false;
            }
        }

        return true;
    }
}