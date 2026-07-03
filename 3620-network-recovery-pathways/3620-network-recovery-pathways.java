import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        
        // Build the adjacency list
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        int maxCost = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            adj[u].add(new int[]{v, cost});
            maxCost = Math.max(maxCost, cost);
        }
        
        // Binary search for the maximum possible minimum-edge cost
        int low = 0, high = maxCost;
        int ans = -1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (check(mid, adj, online, k, n)) {
                ans = mid;      // mid is possible, try to find a larger minimum edge-cost
                low = mid + 1;
            } else {
                high = mid - 1; // mid is too high, look for smaller edge-costs
            }
        }
        
        return ans;
    }
    
    private boolean check(int minEdgeCost, List<int[]>[] adj, boolean[] online, long k, int n) {
        // dp[i] stores the minimum total path cost from node i to node n-1
        Long[] dp = new Long[n];
        long minTotalCost = dfs(0, minEdgeCost, adj, online, n, dp);
        
        return minTotalCost <= k;
    }
    
    private long dfs(int node, int minEdgeCost, List<int[]>[] adj, boolean[] online, int n, Long[] dp) {
        // Destination reached successfully
        if (node == n - 1) {
            return 0;
        }
        
        // If already computed, return the cached result
        if (dp[node] != null) {
            return dp[node];
        }
        
        long minCost = Long.MAX_VALUE;
        
        for (int[] edge : adj[node]) {
            int nextNode = edge[0];
            int edgeCost = edge[1];
            
            // The edge must meet the binary search threshold and the next node must be online
            if (edgeCost >= minEdgeCost && online[nextNode]) {
                long nextCost = dfs(nextNode, minEdgeCost, adj, online, n, dp);
                if (nextCost != Long.MAX_VALUE) {
                    minCost = Math.min(minCost, edgeCost + nextCost);
                }
            }
        }
        
        return dp[node] = minCost;
    }
}