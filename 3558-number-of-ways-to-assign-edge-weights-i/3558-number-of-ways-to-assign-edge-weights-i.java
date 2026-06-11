import java.util.*;
class Solution {
    static final long MOD = 1_000_000_007;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        q.add(new int[]{1, 0}); 
        visited[1] = true;

        int maxDepth = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0];
            int depth = cur[1];

            maxDepth = Math.max(maxDepth, depth);

            for (int nei : graph.get(node)) {
                if (!visited[nei]) {
                    visited[nei] = true;
                    q.add(new int[]{nei, depth + 1});
                }
            }
        }

        if (maxDepth == 0) return 0;

        return (int) modPow(2, maxDepth - 1, MOD);
    }

    private long modPow(long base, long exp, long mod) {
        long result = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }

        return result;
    }
}