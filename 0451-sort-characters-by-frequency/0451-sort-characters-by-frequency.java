class Solution {
    public String frequencySort(String s) {

        int[] count = new int[128];

        // Count frequency
        for (char c : s.toCharArray()) {
            count[c]++;
        }

        StringBuilder ans = new StringBuilder();

        // Run for length of string
        for (int i = 0; i < s.length(); i++) {

            int max = 0;

            // Find character with maximum frequency
            for (int j = 0; j < 128; j++) {
                if (count[j] > count[max]) {
                    max = j;
                }
            }

            // Add character frequency times
            while (count[max] > 0) {
                ans.append((char) max);
                count[max]--;
            }
        }

        return ans.toString();
    }
}
