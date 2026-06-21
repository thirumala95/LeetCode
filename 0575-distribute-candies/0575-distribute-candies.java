import java.util.*;

class Solution {
    public int distributeCandies(int[] candyType) {
        
        HashSet<Integer> set = new HashSet<>();

        // Store unique candy types
        for (int candy : candyType) {
            set.add(candy);
        }

        // Maximum different types she can eat
        return Math.min(set.size(), candyType.length / 2);
    }
}