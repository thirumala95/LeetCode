class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int ans = 0;
        
        for (int i = 0; i < n; i++) {
            int targetCount = 0;
            for (int j = i; j < n; j++) {
                if (nums[j] == target) {
                    targetCount++;
                }
                
                if (targetCount * 2 > (j - i + 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }
}