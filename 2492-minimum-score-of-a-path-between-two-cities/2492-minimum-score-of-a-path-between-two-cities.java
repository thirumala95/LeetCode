import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {
        // Step 1: Build the adjacency list graph
        // Each entry will store: [neighbor_city, road_distance]
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] road : roads) {
            graph.computeIfAbsent(road[0], k -> new ArrayList<>()).add(new int[]{road[1], road[2]});
            graph.computeIfAbsent(road[1], k -> new ArrayList<>()).add(new int[]{road[0], road[2]});
        }
        
        // Step 2: Initialize BFS structures
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int minScore = Integer.MAX_VALUE;
        
        // Start traversal from city 1
        queue.offer(1);
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            // Get all neighbors of the current city
            if (graph.containsKey(current)) {
                for (int[] neighbor : graph.get(current)) {
                    int nextCity = neighbor[0];
                    int distance = neighbor[1];
                    
                    // Track the smallest road encountered in this connected component
                    minScore = Math.min(minScore, distance);
                    
                    // If the neighbor hasn't been visited, add it to the queue
                    if (!visited[nextCity]) {
                        visited[nextCity] = true;
                        queue.offer(nextCity);
                    }
                }
            }
        }
        
        return minScore;
    }
}