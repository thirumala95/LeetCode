import java.util.Arrays;

class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            int newIndex = (i + k) % n;
            result[newIndex] = nums[i];
        }
        for (int i = 0; i < n; i++) {
            nums[i] = result[i];
        }
    }
}