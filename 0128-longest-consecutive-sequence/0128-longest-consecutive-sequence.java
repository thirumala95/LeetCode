class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length==0) {
            return 0;
        }
        Arrays.sort(nums);
        int maxlen=1;
        int cnt=1;
        for(int i=1;i<nums.length;i++) {
            if(nums[i]==nums[i-1]+1) {
                cnt++;
                maxlen=Math.max(maxlen, cnt);
            }
            else if(nums[i]==nums[i-1]) {
                continue;
            }
            else {
                cnt=1;
            }
        }
        return maxlen;
    }
}