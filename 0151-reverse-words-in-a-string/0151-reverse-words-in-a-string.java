class Solution {
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+"); // split by 1 or more spaces
        StringBuilder sb = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i != 0) sb.append(" "); // add space between words
        }

        return sb.toString();
    }
}
