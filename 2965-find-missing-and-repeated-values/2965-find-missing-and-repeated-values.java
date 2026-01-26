class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int size = n * n;
        int repeated = -1, missing = -1;
        
        // Use a single array to mark numbers
        int[] freq = new int[size + 1];
        
        for (int[] row : grid) {
            for (int num : row) {
                freq[num]++;
            }
        }
        
        for (int i = 1; i <= size; i++) {
            if (freq[i] == 2) repeated = i;
            if (freq[i] == 0) missing = i;
        }
        
        return new int[]{repeated, missing};
    }
}
