class Solution {

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);  // optional but good practice
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list,
                           List<Integer> tempList,
                           int[] nums,
                           int remain,
                           int start) {

        if (remain == 0) {
            list.add(new ArrayList<>(tempList));
            return;
        }

        if (remain < 0) {
            return;
        }

        for (int i = start; i < nums.length; i++) {

            // choose the number
            tempList.add(nums[i]);

            // stay at same index (i) because unlimited reuse allowed
            backtrack(list, tempList, nums, remain - nums[i], i);

            // backtrack (remove last added number)
            tempList.remove(tempList.size() - 1);
        }
    }
}