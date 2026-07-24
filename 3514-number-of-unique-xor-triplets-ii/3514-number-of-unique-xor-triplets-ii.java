import java.util.HashSet;
import java.util.Set;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
      
        Set<Integer> uniqueNumsSet = new HashSet<>();
        for (int num : nums) {
            uniqueNumsSet.add(num);
        }
        
        int[] U = new int[uniqueNumsSet.size()];
        int idx = 0;
        for (int num : uniqueNumsSet) {
            U[idx++] = num;
        }
        boolean[] round2 = new boolean[2048];
        for (int i = 0; i < U.length; i++) {
            for (int j = i; j < U.length; j++) {
                round2[U[i] ^ U[j]] = true;
            }
        }

        boolean[] round3 = new boolean[2048];
        for (int p = 0; p < 2048; p++) {
            if (round2[p]) {
                for (int u : U) {
                    round3[p ^ u] = true;
                }
            }
        }

        
        int ans = 0;
        for (boolean b : round3) {
            if (b) {
                ans++;
            }
        }

        return ans;
    }
}