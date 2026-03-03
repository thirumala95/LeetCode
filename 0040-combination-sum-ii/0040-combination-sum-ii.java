import java.util.*;

class Solution {

    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);   // Step 1: sort
        solve(0, target, nums, new ArrayList<>(), ans);
        return ans;
    }

    void solve(int start, int target, int[] nums,
               List<Integer> list,
               List<List<Integer>> ans) {

        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < nums.length; i++) {

            // Step 2: skip duplicates
            if (i > start && nums[i] == nums[i - 1]) continue;

            if (nums[i] > target) break;

            list.add(nums[i]);

            // Step 3: i + 1 (use once)
            solve(i + 1, target - nums[i], nums, list, ans);

            list.remove(list.size() - 1);  // backtrack
        }
    }
}