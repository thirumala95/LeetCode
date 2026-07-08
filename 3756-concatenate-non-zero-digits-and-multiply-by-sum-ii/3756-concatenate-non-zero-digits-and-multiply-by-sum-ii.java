import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();
        long MOD = 1_000_000_007L;

        
        List<Integer> digits = new ArrayList<>();
        List<Integer> origIndices = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            char ch = s.charAt(i);
            if (ch != '0') {
                digits.add(ch - '0');
                origIndices.add(i);
            }
        }

        int n = digits.size();

       
        long[] prefSum = new long[n + 1];
        long[] prefVal = new long[n + 1];
        long[] pow10 = new long[n + 1];
        
        pow10[0] = 1;
        for (int i = 0; i < n; i++) {
            prefSum[i + 1] = prefSum[i] + digits.get(i);
            prefVal[i + 1] = (prefVal[i] * 10 + digits.get(i)) % MOD;
            pow10[i + 1] = (pow10[i] * 10) % MOD;
        }

        
        int numQueries = queries.length;
        int[] answer = new int[numQueries];

        for (int i = 0; i < numQueries; i++) {
            int li = queries[i][0];
            int ri = queries[i][1];

            
            int L = lowerBound(origIndices, li);
            
            int R = upperBound(origIndices, ri) - 1;

            
            if (L > R) {
                answer[i] = 0;
                continue;
            }

            // Calculate x % MOD
            long len = R - L + 1;
            long x = (prefVal[R + 1] - (prefVal[L] * pow10[(int) len]) % MOD + MOD) % MOD;

            // Calculate sum of digits
            long sum = prefSum[R + 1] - prefSum[L];

            // Final answer for the query
            answer[i] = (int) ((x * (sum % MOD)) % MOD);
        }

        return answer;
    }

    // Standard binary search for finding the first element >= target
    private int lowerBound(List<Integer> list, int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    private int upperBound(List<Integer> list, int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}