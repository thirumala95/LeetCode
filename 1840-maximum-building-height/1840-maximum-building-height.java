import java.util.Arrays;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        // 1. Handle base cases and edge additions
        int m = restrictions.length;
        if (m == 0) {
            return n - 1; // No restrictions, heights can go 0, 1, 2, ..., n-1
        }
        
        // Create an extended list of restrictions to include building 1 and building n
        int[][] extended = new int[m + 2][2];
        extended[0] = new int[]{1, 0}; // Building 1 always has height 0
        extended[1] = new int[]{n, n - 1}; // Building n can at most be n - 1
        
        for (int i = 0; i < m; i++) {
            extended[i + 2] = restrictions[i];
        }
        
        // Sort restrictions by building ID
        Arrays.sort(extended, (a, b) -> Integer.compare(a[0], b[0]));
        
        int len = extended.length;
        
        // 2. Left-to-Right Pass: Restrict heights based on left neighbors
        for (int i = 1; i < len; i++) {
            int dist = extended[i][0] - extended[i - 1][0];
            extended[i][1] = Math.min(extended[i][1], extended[i - 1][1] + dist);
        }
        
        // 3. Right-to-Left Pass: Restrict heights based on right neighbors
        for (int i = len - 2; i >= 0; i--) {
            int dist = extended[i + 1][0] - extended[i][0];
            extended[i][1] = Math.min(extended[i][1], extended[i + 1][1] + dist);
        }
        
        // 4. Find the absolute maximum height possible between any two restricted nodes
        int maxHeight = 0;
        for (int i = 0; i < len - 1; i++) {
            int id1 = extended[i][0], h1 = extended[i][1];
            int id2 = extended[i + 1][0], h2 = extended[i + 1][1];
            
            // Formula to calculate peak height between two points
            int peak = (h1 + h2 + (id2 - id1)) / 2;
            maxHeight = Math.max(maxHeight, peak);
        }
        
        return maxHeight;
    }
}