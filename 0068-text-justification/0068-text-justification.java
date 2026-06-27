import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;
        
        while (i < words.length) {
            int j = i + 1;
            int lineLength = words[i].length();
            
            // Greedy approach: find how many words can fit in the current line
            while (j < words.length && lineLength + 1 + words[j].length() <= maxWidth) {
                lineLength += 1 + words[j].length();
                j++;
            }
            
            StringBuilder sb = new StringBuilder();
            int numWords = j - i;
            
            // Case 1: Last line or the line contains only 1 word -> Left-justified
            if (j == words.length || numWords == 1) {
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) sb.append(" ");
                }
                // Pad remaining spaces to the right
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
            } 
            // Case 2: Middle line with more than 1 word -> Fully-justified
            else {
                int totalWordsLength = 0;
                for (int k = i; k < j; k++) {
                    totalWordsLength += words[k].length();
                }
                
                int totalSpaces = maxWidth - totalWordsLength;
                int gaps = numWords - 1;
                
                int baseSpaces = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;
                
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        // Add base spaces + 1 extra space if available for leftmost slots
                        int spacesToApply = baseSpaces + (k - i < extraSpaces ? 1 : 0);
                        for (int s = 0; s < spacesToApply; s++) {
                            sb.append(" ");
                        }
                    }
                }
            }
            
            result.add(sb.toString());
            i = j; // Move to the next set of words
        }
        
        return result;
    }
}