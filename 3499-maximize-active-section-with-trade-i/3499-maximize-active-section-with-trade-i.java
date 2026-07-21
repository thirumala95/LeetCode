import java.util.ArrayList;
import java.util.List;

class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int initialOnes = 0;
        int n = s.length();
        
        // Count total initial '1's
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                initialOnes++;
            }
        }

        // Break string into contiguous segments: [type (0 or 1), length]
        List<int[]> segments = new ArrayList<>();
        int i = 0;
        while (i < n) {
            char c = s.charAt(i);
            int j = i;
            while (j < n && s.charAt(j) == c) {
                j++;
            }
            segments.add(new int[]{c - '0', j - i});
            i = j;
        }

        int maxDelta = 0;

        // Look for '1' segments surrounded by '0' segments on both sides
        for (int k = 1; k < segments.size() - 1; k++) {
            if (segments.get(k)[0] == 1 && segments.get(k - 1)[0] == 0 && segments.get(k + 1)[0] == 0) {
                int leftZeroLen = segments.get(k - 1)[1];
                int rightZeroLen = segments.get(k + 1)[1];
                maxDelta = Math.max(maxDelta, leftZeroLen + rightZeroLen);
            }
        }

        return initialOnes + maxDelta;
    }
}