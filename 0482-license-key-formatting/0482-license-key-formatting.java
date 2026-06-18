class Solution {
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int charCount = 0;
        
        // Iterate from the back of the string
        for (int i = s.length() - 1; i >= 0; i--) {
            char curr = s.charAt(i);
            
            // Skip existing dashes
            if (curr == '-') {
                continue;
            }
            
            // If we have already filled a group of size k, insert a dash
            if (charCount > 0 && charCount % k == 0) {
                sb.append('-');
            }
            
            // Append the character in uppercase
            sb.append(Character.toUpperCase(curr));
            charCount++;
        }
        
        // Reverse the result since we processed it backwards
        return sb.reverse().toString();
    }
}