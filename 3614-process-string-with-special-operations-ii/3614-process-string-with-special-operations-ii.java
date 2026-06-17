class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] lengths = new long[n];
        long currentLen = 0;

        // Pass 1: Compute lengths
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (Character.isLowerCase(ch)) {
                currentLen++;
            } else if (ch == '*') {
                if (currentLen > 0) currentLen--;
            } else if (ch == '#') {
                currentLen *= 2;
            }
            lengths[i] = currentLen;
        }

        // Out of bounds check
        if (k < 0 || k >= currentLen) {
            return '.';
        }

        // Pass 2: Backtrack from the end
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);

            if (Character.isLowerCase(ch)) {
                if (lengths[i] - 1 == k) {
                    return ch;
                }
            } else if (ch == '#') {
                long half = lengths[i] / 2;
                if (k >= half) {
                    k -= half;
                }
            } else if (ch == '%') {
                k = lengths[i] - 1 - k;
            }
        }

        return '.';
    }
}