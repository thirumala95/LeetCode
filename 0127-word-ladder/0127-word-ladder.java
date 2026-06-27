import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert the list to a set for O(1) lookups and removals
        Set<String> dict = new HashSet<>(wordList);
        
        // If the destination word is not in the dictionary, no valid path exists
        if (!dict.contains(endWord)) {
            return 0;
        }
        
        // BFS Queue to store the words at the current transformation level
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        
        // Track the number of words in the transformation sequence
        int level = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // Process all words at the current BFS level
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                
                // If we reached the target word, return the sequence length
                if (currentWord.equals(endWord)) {
                    return level;
                }
                
                // Try mutating every character of the current word
                char[] wordChars = currentWord.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;
                        
                        wordChars[j] = c;
                        String nextWord = String.valueOf(wordChars);
                        
                        // If the mutated word is in the dictionary, it's a valid next step
                        if (dict.contains(nextWord)) {
                            queue.add(nextWord);
                            dict.remove(nextWord); // Remove to prevent visiting it again
                        }
                    }
                    // Restore the original character for the next position's mutations
                    wordChars[j] = originalChar;
                }
            }
            // Increment the level after completely exploring the current depth
            level++;
        }
        
        return 0;
    }
}