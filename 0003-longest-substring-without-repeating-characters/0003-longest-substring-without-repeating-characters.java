class Solution {
    public int lengthOfLongestSubstring(String s) {

        int[] lastIndex = new int[256];
        for (int i = 0; i < 256; i++) lastIndex[i] = -1;

        int l = 0, maxLen = 0;

        for (int r = 0; r < s.length(); r++) {

            char c = s.charAt(r);

            if (lastIndex[c] >= l) {
                l = lastIndex[c] + 1;
            }

            lastIndex[c] = r;

            maxLen = Math.max(maxLen, r - l + 1);
        }

        return maxLen;
    }
}