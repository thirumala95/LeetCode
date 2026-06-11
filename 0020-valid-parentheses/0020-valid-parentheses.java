import java.util.Stack;

class Solution {
    public boolean isValid(String s) {

        Stack<Character> st = new Stack<>();

        for (char c : s.toCharArray()) {

            // opening brackets
            if (c == '(' || c == '{' || c == '[') {
                st.push(c);
            } 
            else {
                // closing bracket → stack must not be empty
                if (st.isEmpty()) return false;

                char top = st.pop();

                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }

        // stack must be empty at end
        return st.isEmpty();
    }
}