import java.util.*;

class Solution {
    public List<Integer> splitIntoFibonacci(String num) {
        List<Integer> ans = new ArrayList<>();
        solve(num, 0, ans);
        return ans;
    }

    private boolean solve(String num, int index, List<Integer> ans) {

        if (index == num.length())
            return ans.size() >= 3;

        long curr = 0;

        for (int i = index; i < num.length(); i++) {

            if (i > index && num.charAt(index) == '0')
                break;

            curr = curr * 10 + (num.charAt(i) - '0');

            if (curr > Integer.MAX_VALUE)
                break;

            int size = ans.size();

            if (size >= 2) {
                long sum = (long)ans.get(size - 1) + ans.get(size - 2);

                if (curr < sum)
                    continue;

                if (curr > sum)
                    break;
            }

            ans.add((int) curr);

            if (solve(num, i + 1, ans))
                return true;

            ans.remove(ans.size() - 1);
        }

        return false;
    }
}