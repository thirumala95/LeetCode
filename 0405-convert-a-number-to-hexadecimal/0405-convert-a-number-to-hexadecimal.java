class Solution {
    public String toHex(int num) {
        if (num == 0) return "0";
        
        char[] hexMap = "0123456789abcdef".toCharArray();
        StringBuilder sb = new StringBuilder();
        
        // Process the 32-bit integer until all set bits are cleared
        while (num != 0) {
            // Step 1: Extract the lowest 4 bits
            int lastFourBits = num & 0xf;
            
            // Step 2: Map to hex character and append
            sb.append(hexMap[lastFourBits]);
            
            // Step 3: Unsigned right shift by 4 bits
            num >>>= 4;
        }
        
        // The digits were collected from right to left, so reverse the string
        return sb.reverse().toString();
    }
}