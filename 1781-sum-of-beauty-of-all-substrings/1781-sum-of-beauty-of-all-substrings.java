class Solution {
    public int beautySum(String s) {
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            int[] freq = new int[26]; // frequency array for substring starting at i

            for (int j = i; j < s.length(); j++) {
                freq[s.charAt(j) - 'a']++; // increase frequency of current char

                int max = 0, min = Integer.MAX_VALUE;

                // Find max and min frequency in current substring
                for (int k = 0; k < 26; k++) {
                    if (freq[k] > 0) { // ignore characters with 0 count
                        max = Math.max(max, freq[k]);
                        min = Math.min(min, freq[k]);
                    }
                }

                sum += (max - min); // add beauty of this substring
            }
        }

        return sum;
    }
}
