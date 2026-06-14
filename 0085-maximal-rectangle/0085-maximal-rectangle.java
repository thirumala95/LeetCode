class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;

        int m = matrix[0].length, ans = 0;
        int[] h = new int[m];

        for (char[] row : matrix) {
            for (int j = 0; j < m; j++)
                h[j] = row[j] == '1' ? h[j] + 1 : 0;

            ans = Math.max(ans, area(h));
        }

        return ans;
    }

    private int area(int[] h) {
        Stack<Integer> st = new Stack<>();
        int max = 0;

        for (int i = 0; i <= h.length; i++) {
            int cur = (i == h.length) ? 0 : h[i];

            while (!st.isEmpty() && cur < h[st.peek()]) {
                int ht = h[st.pop()];
                int w = st.isEmpty() ? i : i - st.peek() - 1;
                max = Math.max(max, ht * w);
            }
            st.push(i);
        }
        return max;
    }
}