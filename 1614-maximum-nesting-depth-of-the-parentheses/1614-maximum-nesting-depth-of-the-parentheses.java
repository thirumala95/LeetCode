class Solution {
    public int maxDepth(String s) {
        int openBrackets = 0, max = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                 openBrackets++;
            } else if (c == ')') {
                 openBrackets--;
            }
               max = Math.max(max, openBrackets);
        }
        return max;
    }
}
