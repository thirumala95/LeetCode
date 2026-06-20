class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        
        int[] need = new int[26];

        for (char ch : licensePlate.toLowerCase().toCharArray()) {
            if (Character.isLetter(ch)) {
                need[ch - 'a']++;
            }
        }

        String ans = "";

        for (String word : words) {
            
            int[] count = new int[26];

            for (char ch : word.toCharArray()) {
                count[ch - 'a']++;
            }

            boolean valid = true;

            for (int i = 0; i < 26; i++) {
                if (count[i] < need[i]) {
                    valid = false;
                    break;
                }
            }

            if (valid && (ans.equals("") || word.length() < ans.length())) {
                ans = word;
            }
        }

        return ans;
    }
}