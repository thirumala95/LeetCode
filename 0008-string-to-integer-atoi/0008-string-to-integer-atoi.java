class Solution {
    public int myAtoi(String s) {
        int i = 0, sign = 1, result = 0;
        int n = s.length();

        // 1️⃣ Skip leading spaces
        while (i < n && s.charAt(i) == ' ') i++;

        // 2️⃣ Check if next character is '+' or '-'
        if (i < n) {
            if (s.charAt(i) == '-') {
                sign = -1;
                i++;
            } else if (s.charAt(i) == '+') {
                i++;
            }
        }

        // 3️⃣ Read digits and build number
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            // 4️⃣ Handle overflow
            if (result > (Integer.MAX_VALUE - digit) / 10)
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            result = result * 10 + digit;
            i++;
        }

        // 5️⃣ Apply sign and return
        return result * sign;
    }
}
