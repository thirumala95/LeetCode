class Solution {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        if (min == max) return 0;

        // bucket size (ceiling of average gap)
        int gap = (int) Math.ceil((double)(max - min) / (n - 1));

        int bucketCount = (max - min) / gap + 1;

        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        boolean[] used = new boolean[bucketCount];

        for (int num : nums) {
            int idx = (num - min) / gap;

            if (!used[idx]) {
                bucketMin[idx] = num;
                bucketMax[idx] = num;
                used[idx] = true;
            } else {
                bucketMin[idx] = Math.min(bucketMin[idx], num);
                bucketMax[idx] = Math.max(bucketMax[idx], num);
            }
        }

        int maxGap = 0;
        int prevMax = min;

        for (int i = 0; i < bucketCount; i++) {
            if (!used[i]) continue;

            maxGap = Math.max(maxGap, bucketMin[i] - prevMax);
            prevMax = bucketMax[i];
        }

        return maxGap;
    }
}