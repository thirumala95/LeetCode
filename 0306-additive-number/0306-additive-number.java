import java.math.BigInteger;

class Solution {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        if (n < 3) return false;

        // i represents the end index of the first number
        for (int i = 1; i <= n / 2; i++) {
            // Leading zeros are invalid for numbers with length > 1
            if (num.charAt(0) == '0' && i > 1) break;

            // j represents the end index of the second number
            for (int j = i + 1; n - j >= Math.max(i, j - i); j++) {
                // Leading zeros invalid for the second number
                if (num.charAt(i) == '0' && j - i > 1) break;

                String num1Str = num.substring(0, i);
                String num2Str = num.substring(i, j);

                if (isValidSequence(num1Str, num2Str, j, num)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidSequence(String n1Str, String n2Str, int k, String num) {
        BigInteger n1 = new BigInteger(n1Str);
        BigInteger n2 = new BigInteger(n2Str);

        while (k < num.length()) {
            BigInteger sum = n1.add(n2);
            String sumStr = sum.toString();

            // If the remaining string doesn't start with the required sum, it's invalid
            if (!num.startsWith(sumStr, k)) {
                return false;
            }

            // Move forward
            k += sumStr.length();
            n1 = n2;
            n2 = sum;
        }
        return true;
    }
}