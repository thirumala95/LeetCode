import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Step 1: Sort the array to handle duplicates
        Arrays.sort(nums);
        
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>(), result);
        
        return result;
    }
    
    private void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        // Base case: If current permutation is complete
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            // If the element at index i is already used in this path, skip it
            if (used[i]) continue;
            
            // Skip duplicates: If current element is the same as previous, 
            // and the previous element hasn't been processed in this depth level yet.
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            
            // Choose
            used[i] = true;
            current.add(nums[i]);
            
            // Explore
            backtrack(nums, used, current, result);
            
            // Un-choose (Backtrack)
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
}