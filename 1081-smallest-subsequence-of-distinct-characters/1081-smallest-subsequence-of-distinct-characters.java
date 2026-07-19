import java.util.Stack;

class Solution {
    public String smallestSubsequence(String s) {
        // Step 1: Count total occurrences of each character
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        
        // Track characters currently inside the stack
        boolean[] seen = new boolean[26];
        Stack<Character> stack = new Stack<>();
        
        // Step 2: Iterate through the string
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // If the character is already included, we skip it
            if (seen[c - 'a']) {
                continue;
            }
            
            // Pop elements from the stack if they are lexicographically larger 
            // than the current character and appear later in the string
            while (!stack.isEmpty() && stack.peek() > c && lastIndex[stack.peek() - 'a'] > i) {
                seen[stack.pop() - 'a'] = false;
            }
            
            // Add the current character to the stack and mark it as seen
            stack.push(c);
            seen[c - 'a'] = true;
        }
        
        // Step 3: Build the final result string from the stack
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        
        return sb.toString();
    }
}