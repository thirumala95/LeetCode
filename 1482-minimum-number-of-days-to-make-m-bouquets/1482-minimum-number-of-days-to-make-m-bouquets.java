class Solution {
    public int minDays(int[] bloomDay, int m, int k) {

        if ((long)m * k > bloomDay.length) return -1;

        int l = 1, r = 0;

        for (int x : bloomDay) {
            r = Math.max(r, x);
        }

        while (l < r) {
            int mid = l + (r - l) / 2;

            int flowers = 0, bouquets = 0;

            for (int x : bloomDay) {
                if (x <= mid) {
                    flowers++;

                    if (flowers == k) {
                        bouquets++;
                        flowers = 0;
                    }
                } else {
                    flowers = 0;
                }
            }

            if (bouquets >= m) {
                r = mid;
            } else {
                l=  mid + 1;
            }
        }
        return l;
    }
}