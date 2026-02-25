class Solution {
    public int repeatedStringMatch(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        
        // Keep repeating until length >= b
        while (sb.length() < b.length()) {
            sb.append(a);
            count++;
        }
        
        // Check if b is substring
        if (sb.toString().contains(b)) 
            return count;
        
        // Try one more repetition
        sb.append(a);
        count++;
        
        if (sb.toString().contains(b)) 
            return count;
        
        return -1;
    }
}