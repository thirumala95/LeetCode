class Solution {
    public int findPeakElement(int[] nums) {
        int n=nums.length;
        for(int i=1;i<n-1;i++){
            if(nums[i-1]<nums[i] && nums[i]>nums[i+1]){
                return i;
            }
        }
        return nums[0]>nums[n-1] ? 0:n-1;
    }
}
