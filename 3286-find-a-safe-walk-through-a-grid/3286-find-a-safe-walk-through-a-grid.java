import java.util.*;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        
        // minCost[i][j] stores the minimum health lost to reach cell (i, j)
        int[][] minCost = new int[m][n];
        for (int[] row : minCost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        // PriorityQueue stores arrays of format {cost, row, col}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        // Starting point
        int startCost = grid.get(0).get(0);
        minCost[0][0] = startCost;
        pq.offer(new int[]{startCost, 0, 0});
        
        // Direction vectors for moving Up, Down, Left, Right
        int[] dirs = {-1, 0, 1, 0, -1};
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currCost = curr[0];
            int r = curr[1];
            int c = curr[2];
            
            // If we found a worse path to an already processed node, skip it
            if (currCost > minCost[r][c]) continue;
            
            // If we reached the destination, check if we have enough health left
            if (r == m - 1 && c == n - 1) {
                return health - currCost >= 1;
            }
            
            // Explore all 4 neighbors
            for (int i = 0; i < 4; i++) {
                int nr = r + dirs[i];
                int nc = c + dirs[i + 1];
                
                // Check boundaries
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nextCost = currCost + grid.get(nr).get(nc);
                    
                    // If a safer path to the neighbor is found
                    if (nextCost < minCost[nr][nc]) {
                        minCost[nr][nc] = nextCost;
                        pq.offer(new int[]{nextCost, nr, nc});
                    }
                }
            }
        }
        
        return false;
    }
}