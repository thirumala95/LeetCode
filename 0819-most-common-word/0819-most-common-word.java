import java.util.*;

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {

        HashSet<String> set = new HashSet<>(Arrays.asList(banned));
        HashMap<String, Integer> map = new HashMap<>();

        paragraph = paragraph.toLowerCase().replaceAll("[^a-z]", " ");
        String[] words = paragraph.split("\\s+");

        String ans = "";
        int max = 0;

        for (String word : words) {
            if (!set.contains(word)) {
                
                map.put(word, map.getOrDefault(word, 0) + 1);

                if (map.get(word) > max) {
                    max = map.get(word);
                    ans = word;
                }
            }
        }

        return ans;
    }
}