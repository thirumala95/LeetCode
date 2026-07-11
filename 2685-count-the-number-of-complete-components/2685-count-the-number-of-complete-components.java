import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
       
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponentsCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (isCompleteComponent(i, adj, visited)) {
                    completeComponentsCount++;
                }
            }
        }

        return completeComponentsCount;
    }

    private boolean isCompleteComponent(int startNode, List<List<Integer>> adj, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startNode);
        visited[startNode] = true;

        int vertexCount = 0;
        int edgeCount = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            vertexCount++;
            
            edgeCount += adj.get(curr).size();

            for (int neighbor : adj.get(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        
        return edgeCount == (vertexCount * (vertexCount - 1));
    }
}