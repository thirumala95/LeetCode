class Solution {
    public int shipWithinDays(int[] weights, int days) {

        int l = 0, r = 0;

        for (int w : weights) {
            l = Math.max(l, w); // minimum capacity
            r += w;             // maximum capacity
        }

        while (l < r) {
            int mid = l + (r - l) / 2;

            int needDays = 1;
            int currWeight = 0;

            for (int w : weights) {
                if (currWeight + w > mid) {
                    needDays++;
                    currWeight = 0;
                }
                currWeight += w;
            }

            if (needDays <= days) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
}