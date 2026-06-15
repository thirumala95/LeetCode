
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // 1. find decreasing point
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // 2. if not found, reverse whole array
        if (i >= 0) {
            int j = n - 1;

            // find just greater element
            while (nums[j] <= nums[i]) {
                j--;
            }

            // swap
            swap(nums, i, j);
        }

        // 3. reverse right part
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l++, r--);
        }
    }
}
