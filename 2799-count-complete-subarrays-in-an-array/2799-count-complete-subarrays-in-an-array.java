import java.util.HashSet;

class Solution {
    public int countCompleteSubarrays(int[] nums) {

        HashSet<Integer> total = new HashSet<>();

        // Count distinct elements in the whole array
        for (int num : nums) {
            total.add(num);
        }

        int distinct = total.size();
        int count = 0;

        // Check every subarray
        for (int i = 0; i < nums.length; i++) {

            HashSet<Integer> set = new HashSet<>();

            for (int j = i; j < nums.length; j++) {

                set.add(nums[j]);

                if (set.size() == distinct) {
                    count++;
                }
            }
        }

        return count;
    }
}