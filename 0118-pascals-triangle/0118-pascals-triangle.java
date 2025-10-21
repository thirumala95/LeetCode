import java.util.*;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        first.add(1);
        res.add(first);

        for (int r = 1; r < numRows; r++) {
            List<Integer> prev = res.get(r - 1);
            List<Integer> row = new ArrayList<>();
            row.add(1); 
            for (int i = 1; i < prev.size(); i++) {
                row.add(prev.get(i - 1) + prev.get(i));
            }
            row.add(1); 
            res.add(row);
        }

        return res;
    }
}
