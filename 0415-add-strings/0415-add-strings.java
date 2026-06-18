class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        
        // Loop while there are digits left to add or a carry to process
        while (i >= 0 || j >= 0 || carry > 0) {
            int digit1 = (i >= 0) ? num1.charAt(i) - '0' : 0;
            int digit2 = (j >= 0) ? num2.charAt(j) - '0' : 0;
            
            int total = digit1 + digit2 + carry;
            
            carry = total / 10;        // Determine new carry
            sb.append(total % 10);     // Append current digit
            
            i--;
            j--;
        }
        
        // Reverse the string because digits were appended backwards
        return sb.reverse().toString();
    }
}