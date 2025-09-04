class Solution {
    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n]; 
        int posIndex = 0, negIndex = 1;
        for (int i = 0; i < n; i++) {
            if(nums[i] > 0) {
                arr[posIndex] = nums[i];
                posIndex += 2;  
            } else {
                arr[negIndex] = nums[i];
                negIndex += 2; 
            }
        }

        return arr;
    }
}
