class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false; 

        int nums = x;       // store original value
        int reversed = 0;   // to store reversed number

        while (x > 0) {
            int r = x % 10;               // extract last digit
            reversed = r + (reversed * 10); // build reversed number
            x = x / 10;                   // remove last digit
        }

        if (nums == reversed) {
            return true;
        } else {
            return false;
        }
    }
}
