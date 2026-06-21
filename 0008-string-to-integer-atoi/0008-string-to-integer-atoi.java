class Solution {
    public int myAtoi(String s) {

        s = s.trim();

        if (s.length() == 0)
            return 0;

        int sign = 1;
        int i = 0;
        long num = 0;

        // Check sign
        if (s.charAt(0) == '-') {
            sign = -1;
            i++;
        }
        else if (s.charAt(0) == '+') {
            i++;
        }

        // Read digits
        while (i < s.length() && Character.isDigit(s.charAt(i))) {

            num = num * 10 + (s.charAt(i) - '0');

            // Overflow check
            if (sign * num > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;

            if (sign * num < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;

            i++;
        }

        return (int)(sign * num);
    }
}