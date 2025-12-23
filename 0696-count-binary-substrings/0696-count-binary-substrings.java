class Solution {
    public int countBinarySubstrings(String s) {
        int prev = 0, curr = 1, count = 0;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                curr++;               // same group, increase current group size
            } else {
                count += Math.min(prev, curr); // add substrings from previous group
                prev = curr;          // current becomes previous
                curr = 1;             // start new group
            }
        }

        count += Math.min(prev, curr); // add last pair
        return count;
    }
}
