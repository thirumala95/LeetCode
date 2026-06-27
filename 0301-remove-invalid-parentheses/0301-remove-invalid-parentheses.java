import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(s);
        visited.add(s);

        boolean foundMinimumRemovals = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                // If it's valid, add to results and signal that we found the min removal level
                if (isValid(current)) {
                    result.add(current);
                    foundMinimumRemovals = true;
                }

                // If we already found a valid string at this level, 
                // we don't want to generate states for the next level (deeper removals)
                if (foundMinimumRemovals) continue;

                // Generate all possible states by removing one parenthesis
                for (int j = 0; j < current.length(); j++) {
                    char c = current.charAt(j);
                    if (c != '(' && c != ')') continue; // Skip non-parenthesis characters

                    String nextState = current.substring(0, j) + current.substring(j + 1);
                    
                    if (!visited.contains(nextState)) {
                        visited.add(nextState);
                        queue.add(nextState);
                    }
                }
            }

            // Stop processing further levels once the minimum removal strings are found
            if (foundMinimumRemovals) break;
        }

        return result;
    }

    // Helper method to check if a string has valid parentheses
    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) return false; // More closing than opening at any point
            }
        }
        return count == 0;
    }
}