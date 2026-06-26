import java.util.*;

class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        
        // Step 1: Compute Prefix Sums
        int[] pref = new int[n + 1];
        pref[0] = 0;
        for (int i = 0; i < n; i++) {
            int val = (nums[i] == target) ? 1 : -1;
            pref[i + 1] = pref[i] + val;
        }
        
        // Step 2: Coordinate Compression since prefix sums can be negative/large
        // Use a TreeSet to get unique sorted prefix sum values
        TreeSet<Integer> set = new TreeSet<>();
        for (int p : pref) {
            set.add(p);
        }
        
        // Map each unique prefix sum to a 1-based index rank
        Map<Integer, Integer> ranks = new HashMap<>();
        int rank = 1;
        for (int p : set) {
            ranks.put(p, rank++);
        }
        
        // Step 3: Use Fenwick Tree to count pairs where pref[j] > pref[i]
        long ans = 0;
        int m = ranks.size();
        FenwickTree bit = new FenwickTree(m);
        
        for (int p : pref) {
            int currentRank = ranks.get(p);
            // Query how many previous prefix sums have a rank strictly less than currentRank
            ans += bit.query(currentRank - 1);
            // Insert current prefix sum's rank into the BIT
            bit.update(currentRank, 1);
        }
        
        return ans;
    }
}

// Standard Fenwick Tree implementation
class FenwickTree {
    private int[] tree;
    private int size;
    
    public FenwickTree(int size) {
        this.size = size;
        this.tree = new int[size + 1];
    }
    
    public void update(int i, int delta) {
        while (i <= size) {
            tree[i] += delta;
            i += i & (-i);
        }
    }
    
    public int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= i & (-i);
        }
        return sum;
    }
}