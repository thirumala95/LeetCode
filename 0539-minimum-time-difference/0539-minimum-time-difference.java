import java.util.*;

class Solution {
    public int findMinDifference(List<String> timePoints) {
        List<Integer> list = new ArrayList<>();

        for (String s : timePoints) {
            String[] t = s.split(":");
            int mins = Integer.parseInt(t[0]) * 60 +
                       Integer.parseInt(t[1]);
            list.add(mins);
        }

        Collections.sort(list);

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i < list.size(); i++) {
            ans = Math.min(ans, list.get(i) - list.get(i - 1));
        }

        // check circular difference (last → first)
        ans = Math.min(ans,
                1440 - list.get(list.size() - 1) + list.get(0));

        return ans;
    }
}