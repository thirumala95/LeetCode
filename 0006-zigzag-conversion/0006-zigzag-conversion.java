class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;

        StringBuilder res = new StringBuilder();
        int cycle = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = i; j < s.length(); j += cycle) {
                res.append(s.charAt(j));
                int diag = j + cycle - 2 * i;
                if (i != 0 && i != numRows - 1 && diag < s.length())
                    res.append(s.charAt(diag));
            }
        }
        return res.toString();
    }
}
