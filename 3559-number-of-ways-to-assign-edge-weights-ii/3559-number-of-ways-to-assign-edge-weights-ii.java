class Solution {
    static final int MOD = 1_000_000_007;
    static final int LOG = 18;
    
    int[] depth;
    int[][] up;
    List<Integer>[] adj;
    
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        
        depth = new int[n + 1];
        up = new int[n + 1][LOG];
        
        // BFS to set depth and parent (up[i][0])
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        visited[1] = true;
        up[1][0] = 1; // root's parent is itself
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int nei : adj[node]) {
                if (!visited[nei]) {
                    visited[nei] = true;
                    depth[nei] = depth[node] + 1;
                    up[nei][0] = node;
                    queue.offer(nei);
                }
            }
        }
        
        // Build binary lifting table
        for (int j = 1; j < LOG; j++)
            for (int i = 1; i <= n; i++)
                up[i][j] = up[up[i][j-1]][j-1];
        
        // Precompute powers of 2
        long[] pow2 = new long[n + 1];
        pow2[0] = 1;
        for (int i = 1; i <= n; i++)
            pow2[i] = pow2[i-1] * 2 % MOD;
        
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0], v = queries[i][1];
            int lca = lca(u, v);
            int len = depth[u] + depth[v] - 2 * depth[lca];
            ans[i] = len == 0 ? 0 : (int) pow2[len - 1];
        }
        return ans;
    }
    
    int lca(int u, int v) {
        if (depth[u] < depth[v]) { int t = u; u = v; v = t; }
        int diff = depth[u] - depth[v];
        for (int j = 0; j < LOG; j++)
            if (((diff >> j) & 1) == 1) u = up[u][j];
        if (u == v) return u;
        for (int j = LOG - 1; j >= 0; j--)
            if (up[u][j] != up[v][j]) { u = up[u][j]; v = up[v][j]; }
        return up[u][0];
    }
}