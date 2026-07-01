import java.util.*;

class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        
        // If the start or end cell has a thief, the safeness factor is immediately 0
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }

        int[][] safeness = new int[n][n];
        for (int[] row : safeness) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new LinkedList<>();

        // Step 1: Initialize Multi-source BFS from all thieves
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    queue.offer(new int[]{r, c});
                    safeness[r][c] = 0;
                }
            }
        }

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // Populate the minimum distance to any thief for each cell
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && safeness[nr][nc] == Integer.MAX_VALUE) {
                    safeness[nr][nc] = safeness[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        // Step 2: Modified Dijkstra using a Max-Heap to maximize the minimum safeness factor
        // The heap stores elements as {safeness_factor, row, col} sorted descending by safeness_factor
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        boolean[][] visited = new boolean[n][n];

        maxHeap.offer(new int[]{safeness[0][0], 0, 0});
        visited[0][0] = true;

        while (!maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            int maxSafenessSoFar = curr[0];
            int r = curr[1];
            int c = curr[2];

            // If we reached the bottom-right destination, return the result
            if (r == n - 1 && c == n - 1) {
                return maxSafenessSoFar;
            }

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    // The bottleneck safeness of the path is the minimum element encountered
                    int nextSafeness = Math.min(maxSafenessSoFar, safeness[nr][nc]);
                    maxHeap.offer(new int[]{nextSafeness, nr, nc});
                }
            }
        }

        return 0;
    }
}