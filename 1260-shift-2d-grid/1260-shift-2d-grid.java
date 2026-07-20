import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int totalElements = m * n;
        
        // Effective shifts needed
        k = k % totalElements;
        
        List<List<Integer>> result = new ArrayList<>();
        
        // Initialize the nested lists
        for (int i = 0; i < m; i++) {
            result.add(new ArrayList<>());
        }
        
        for (int i = 0; i < totalElements; i++) {
            // Find which element from the original grid lands at the current 1D index 'i'
            // We shift backward by k to find the original source position
            int original1DIndex = (i - k + totalElements) % totalElements;
            
            int r = original1DIndex / n;
            int c = original1DIndex % n;
            
            // Determine which row in the result list this 1D index 'i' belongs to
            int targetRow = i / n;
            result.get(targetRow).add(grid[r][c]);
        }
        
        return result;
    }
}