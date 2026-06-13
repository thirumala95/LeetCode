class Solution {

    Boolean[][] memo;

    public boolean isMatch(String s, String p) {

        memo =
            new Boolean[s.length() + 1]
                       [p.length() + 1];

        return solve(0, 0, s, p);
    }

    private boolean solve(
            int i,
            int j,
            String s,
            String p) {

        if (memo[i][j] != null)
            return memo[i][j];

        boolean ans;

        if (j == p.length()) {

            ans = (i == s.length());

        } else {

            boolean firstMatch =
                i < s.length() &&
                (s.charAt(i) == p.charAt(j)
                 || p.charAt(j) == '.');

            if (j + 1 < p.length()
                && p.charAt(j + 1) == '*') {

                ans =
                    solve(i, j + 2, s, p)
                    ||
                    (firstMatch
                     && solve(i + 1, j, s, p));

            } else {

                ans =
                    firstMatch
                    &&
                    solve(i + 1,
                          j + 1,
                          s,
                          p);
            }
        }

        return memo[i][j] = ans;
    }
}