class Solution {
    public String convertToBase7(int num) {
        if (num == 0)
            return "0";

        boolean neg = num < 0;
        num = Math.abs(num);

        String ans = "";

        while (num > 0) {
            ans = (num % 7) + ans;
            num /= 7;
        }

        return neg ? "-" + ans : ans;
    }
}