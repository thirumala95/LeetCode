import java.util.Arrays;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Pack nums[i] and original index i into a long to sort them together efficiently
        long[] packed = new long[n];
        for (int i = 0; i < n; i++) {
            packed[i] = ((long) nums[i] << 32) | i;
        }
        Arrays.sort(packed);

        int[] P = new int[n];   // Maps sorted position -> original index
        int[] val = new int[n]; // Sorted values
        int[] pos = new int[n]; // Maps original index -> sorted position

        for (int i = 0; i < n; i++) {
            val[i] = (int) (packed[i] >>> 32);
            P[i] = (int) (packed[i] & 0xFFFFFFFFL);
            pos[P[i]] = i;
        }

        // up[j][i] stores the position reached after 2^j greedy steps from position i
        int[][] up = new int[20][n];
        
        // Two-pointer approach to find the furthest reachable index (2^0 step)
        int r = 0;
        for (int l = 0; l < n; l++) {
            while (r < n && val[r] - val[l] <= maxDiff) {
                r++;
            }
            up[0][l] = r - 1;
        }

        // Build the binary lifting table
        for (int j = 1; j < 20; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            if (u == v) {
                ans[i] = 0;
                continue;
            }

            // Ensure nums[u] <= nums[v] since the graph is undirected
            if (nums[u] > nums[v]) {
                int tmp = u; u = v; v = tmp;
            }

            // If they have the same value, they are connected directly (distance 1)
            if (nums[u] == nums[v]) {
                ans[i] = 1;
                continue;
            }

            int curr = pos[u];
            int targetVal = nums[v];
            int steps = 0;

            // Lift upwards as long as the value remains strictly less than targetVal
            for (int j = 19; j >= 0; j--) {
                int nxt = up[j][curr];
                if (val[nxt] < targetVal) {
                    steps += (1 << j);
                    curr = nxt;
                }
            }

            // Take one final step to reach or exceed targetVal
            steps++;
            curr = up[0][curr];

            if (val[curr] < targetVal) {
                ans[i] = -1; // Target value is unreachable
            } else {
                ans[i] = steps;
            }
        }

        return ans;
    }
}