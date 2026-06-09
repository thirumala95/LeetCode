class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1;
        int r = 0;

        for (int pile : piles) {
            r = Math.max(r, pile);
        }

        while (l < r) {
            int mid = l + (r - l) / 2;

            long hours = 0;
            for (int pile : piles) {
                hours += (pile + mid - 1) / mid; 
            }

            if (hours <= h) {
                r = mid;      
            } else {
                l = mid + 1;  
            }
        }

        return l;
    }
}