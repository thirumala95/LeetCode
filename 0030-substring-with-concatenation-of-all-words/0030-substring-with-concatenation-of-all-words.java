import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;
        int sLen = s.length();

        // 1. Build frequency map for the target words
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        // 2. Run the sliding window for each unique offset
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            Map<String, Integer> currentCounts = new HashMap<>();
            int count = 0; // Tracks number of valid words in current window

            while (right + wordLen <= sLen) {
                // Extract the next word candidate
                String word = s.substring(right, right + wordLen);
                right += wordLen;

                if (wordCounts.containsKey(word)) {
                    currentCounts.put(word, currentCounts.getOrDefault(word, 0) + 1);
                    count++;

                    // If we have more occurrences than needed, shrink from the left
                    while (currentCounts.get(word) > wordCounts.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        currentCounts.put(leftWord, currentCounts.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }

                    // If all words are perfectly matched
                    if (count == wordCount) {
                        result.add(left);
                        // Slide left forward by one word to look for the next match
                        String leftWord = s.substring(left, left + wordLen);
                        currentCounts.put(leftWord, currentCounts.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }
                } else {
                    // Invalid word encountered: Reset the window completely
                    currentCounts.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }
}