class Solution {
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        // Array to track counts of digits 0-9
        int[] counts = new int[10]; 
        
        for (int i = 0; i < secret.length(); i++) {
            int sDigit = secret.charAt(i) - '0';
            int gDigit = guess.charAt(i) - '0';
            
            if (sDigit == gDigit) {
                bulls++;
            } else {
                // If sDigit was previously seen in guess, it's a cow
                if (counts[sDigit] < 0) {
                    cows++;
                }
                // If gDigit was previously seen in secret, it's a cow
                if (counts[gDigit] > 0) {
                    cows++;
                }
                
                // Track current digits: secret increments, guess decrements
                counts[sDigit]++;
                counts[gDigit]--;
            }
        }
        
        return bulls + "A" + cows + "B";
    }
}