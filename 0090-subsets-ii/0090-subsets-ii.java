import java.util.*;

class Solution {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);  // required for duplicates
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int start,
                           List<Integer> list,
                           List<List<Integer>> res) {

        res.add(new ArrayList<>(list));

        for (int i = start; i < nums.length; i++) {

            if (i > start && nums[i] == nums[i - 1]) continue;

            list.add(nums[i]);
            backtrack(nums, i + 1, list, res);
            list.remove(list.size() - 1);
        }
    }
}