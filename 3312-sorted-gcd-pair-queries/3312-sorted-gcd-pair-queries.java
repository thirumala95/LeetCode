import java.util.Arrays;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }

        // Step 1: Count frequency of each number
        int[] freq = new int[maxVal + 1];
        for (int num : nums) {
            freq[num]++;
        }

        // Step 2: Count how many numbers are multiples of each i
        long[] gcdCount = new long[maxVal + 1];
        for (int i = maxVal; i >= 1; i--) {
            long countMultiples = 0;
            for (int j = i; j <= maxVal; j += i) {
                countMultiples += freq[j];
            }
            
            // Total pairs whose GCD is a multiple of i
            long totalPairs = (countMultiples * (countMultiples - 1)) / 2;
            
            // Inclusion-Exclusion: Subtract pairs with GCD as 2i, 3i, etc.
            for (int j = 2 * i; j <= maxVal; j += i) {
                totalPairs -= gcdCount[j];
            }
            gcdCount[i] = totalPairs;
        }

        // Step 3: Compute Prefix Sums of the GCD pair counts
        long[] prefixSums = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSums[i] = prefixSums[i - 1] + gcdCount[i];
        }

        // Step 4: Answer each query using binary search
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i];
            
            // Binary search to find the smallest GCD index where prefixSums[gcd] > target
            int low = 1, high = maxVal, res = maxVal;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixSums[mid] > target) {
                    res = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            answer[i] = res;
        }

        return answer;
    }
}