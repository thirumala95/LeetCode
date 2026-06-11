import java.util.HashMap;

class Solution {

    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int res = 0;
        int distinct = 0;

        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];

            if (!freq.containsKey(num) || freq.get(num) == 0) {
                distinct++;
            }

            freq.put(num, freq.getOrDefault(num, 0) + 1);

            while (distinct > k) {
                int leftNum = nums[left];
                freq.put(leftNum, freq.get(leftNum) - 1);

                if (freq.get(leftNum) == 0) {
                    distinct--;
                }
                left++;
            }

            res += (right - left + 1);
        }

        return res;
    }
}