import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;

        // DP tables initialized with -1 for unreachable states
        int[][] maxScore = new int[n][n];
        int[][] pathCount = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxScore[i][j] = -1;
            }
        }

        // Base case: Start at the bottom-right corner 'S'
        maxScore[n - 1][n - 1] = 0;
        pathCount[n - 1][n - 1] = 1;

        // Iterate backward from bottom-right up to top-left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // If it's an obstacle or unreachable, skip pushing values forward
                if (board.get(i).charAt(j) == 'X' || maxScore[i][j] == -1) {
                    continue;
                }

                int currentScore = maxScore[i][j];
                int currentPaths = pathCount[i][j];

                // Possible next directions: up, left, up-left
                int[][] directions = {{-1, 0}, {0, -1}, {-1, -1}};

                for (int[] dir : directions) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];

                    // Check bounds and obstacle constraint
                    if (ni >= 0 && nj >= 0 && board.get(ni).charAt(nj) != 'X') {
                        char cellChar = board.get(ni).charAt(nj);
                        int cellValue = (cellChar == 'E' || cellChar == 'S') ? 0 : (cellChar - '0');
                        int nextScore = currentScore + cellValue;

                        if (nextScore > maxScore[ni][nj]) {
                            maxScore[ni][nj] = nextScore;
                            pathCount[ni][nj] = currentPaths;
                        } else if (nextScore == maxScore[ni][nj]) {
                            pathCount[ni][nj] = (pathCount[ni][nj] + currentPaths) % MOD;
                        }
                    }
                }
            }
        }

        // Return results from destination 'E' at (0, 0)
        if (maxScore[0][0] == -1) {
            return new int[]{0, 0};
        }
        return new int[]{maxScore[0][0], pathCount[0][0]};
    }
}