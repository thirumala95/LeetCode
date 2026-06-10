import java.util.*;

class Solution {

    // sum of max([l..r]) for l = left..r, using blocksize deque
    // When we advance left by 1: subtract contribution, reduce front block by 1
    static long removeLeft(Deque<long[]> dq, long[] runSum) {
        // dq entries: [val, blockSize]; runSum[0] = current sum
        long[] front = dq.peekFirst();
        runSum[0] -= front[0];
        front[1]--;
        if (front[1] == 0) dq.pollFirst();
        return runSum[0];
    }

    // Returns {count, sum} where count = # subarrays with max-min >= thr,
    // sum = total of (max-min) for those subarrays
    private long[] countAndSum(int[] nums, long thr) {
        int n = nums.length;
        long count = 0, sumVal = 0;

        // Two deques for the "bad" window [left..right] where max-min < thr
        // Track sum of max and sum of min over all starting points in that window
        Deque<long[]> mxBad = new ArrayDeque<>(); // [val, blockSize], sum of max
        Deque<long[]> mnBad = new ArrayDeque<>(); // [val, blockSize], sum of min
        long[] smxBad = {0}, smnBad = {0};

        // Two deques for full prefix [0..right]
        Deque<long[]> mxFull = new ArrayDeque<>();
        Deque<long[]> mnFull = new ArrayDeque<>();
        long[] smxFull = {0}, smnFull = {0};

        int left = 0; // left boundary of bad window

        for (int r = 0; r < n; r++) {
            long v = nums[r];

            // Update full deques
            long blk = 1;
            while (!mxFull.isEmpty() && mxFull.peekLast()[0] <= v) {
                long[] e = mxFull.pollLast(); smxFull[0] -= e[0] * e[1]; blk += e[1];
            }
            mxFull.addLast(new long[]{v, blk}); smxFull[0] += v * blk;

            blk = 1;
            while (!mnFull.isEmpty() && mnFull.peekLast()[0] >= v) {
                long[] e = mnFull.pollLast(); smnFull[0] -= e[0] * e[1]; blk += e[1];
            }
            mnFull.addLast(new long[]{v, blk}); smnFull[0] += v * blk;

          
            blk = 1;
            while (!mxBad.isEmpty() && mxBad.peekLast()[0] <= v) {
                long[] e = mxBad.pollLast(); smxBad[0] -= e[0] * e[1]; blk += e[1];
            }
            mxBad.addLast(new long[]{v, blk}); smxBad[0] += v * blk;

            blk = 1;
            while (!mnBad.isEmpty() && mnBad.peekLast()[0] >= v) {
                long[] e = mnBad.pollLast(); smnBad[0] -= e[0] * e[1]; blk += e[1];
            }
            mnBad.addLast(new long[]{v, blk}); smnBad[0] += v * blk;

            while (!mxBad.isEmpty() && !mnBad.isEmpty()
                    && mxBad.peekFirst()[0] - mnBad.peekFirst()[0] >= thr) {
                removeLeft(mxBad, smxBad);
                removeLeft(mnBad, smnBad);
                left++;
            }

            count += left;
           
            sumVal += (smxFull[0] - smxBad[0]) - (smnFull[0] - smnBad[0]);
        }
        return new long[]{count, sumVal};
    }

    public long maxTotalValue(int[] nums, int k) {
        
        long lo = 0, hi = 1_000_000_000L;
        while (lo < hi) {
            long mid = (lo + hi + 1) / 2;
            if (countAndSum(nums, mid)[0] >= k) lo = mid;
            else hi = mid - 1;
        }
        long thr = lo;
        long[] res = countAndSum(nums, thr + 1);
        long cntAbove = res[0], sumAbove = res[1];
        return sumAbove + (k - cntAbove) * thr;
    }
}