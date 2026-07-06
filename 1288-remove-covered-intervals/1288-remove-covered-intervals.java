import java.util.Arrays;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort: ascending by start point; if tied, descending by end point
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            } else {
                return Integer.compare(b[1], a[1]);
            }
        });
        
        int remainingCount = 0;
        int maxEnd = 0;
        
        for (int[] interval : intervals) {
            // If the current interval's end is greater than maxEnd,
            // it is not covered by any previous interval.
            if (interval[1] > maxEnd) {
                remainingCount++;
                maxEnd = interval[1]; // Update the boundary
            }
        }
        
        return remainingCount;
    }
}