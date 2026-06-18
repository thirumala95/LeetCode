class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        // Optimization: If ransomNote is longer than magazine, it's impossible
        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        // Array to store counts of characters 'a' through 'z'
        int[] charCounts = new int[26];

        // Populate frequencies of characters in the magazine
        for (int i = 0; i < magazine.length(); i++) {
            charCounts[magazine.charAt(i) - 'a']++;
        }

        // Deduct frequencies for each character used by the ransom note
        for (int i = 0; i < ransomNote.length(); i++) {
            int index = ransomNote.charAt(i) - 'a';
            charCounts[index]--;

            // If a character count drops below 0, magazine lacks this character
            if (charCounts[index] < 0) {
                return false;
            }
        }

        return true;
    }
}