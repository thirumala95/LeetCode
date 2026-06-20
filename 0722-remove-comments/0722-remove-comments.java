import java.util.*;

class Solution {
    public List<String> removeComments(String[] source) {
        
        List<String> ans = new ArrayList<>();
        boolean block = false;
        StringBuilder sb = new StringBuilder();

        for (String line : source) {
            
            if (!block) sb = new StringBuilder();

            for (int i = 0; i < line.length(); i++) {

                if (!block && i + 1 < line.length() &&
                    line.charAt(i) == '/' && line.charAt(i + 1) == '*') {
                    
                    block = true;
                    i++;
                }

                else if (block && i + 1 < line.length() &&
                        line.charAt(i) == '*' && line.charAt(i + 1) == '/') {
                    
                    block = false;
                    i++;
                }

                else if (!block && i + 1 < line.length() &&
                        line.charAt(i) == '/' && line.charAt(i + 1) == '/') {
                    
                    break;
                }

                else if (!block) {
                    sb.append(line.charAt(i));
                }
            }

            if (!block && sb.length() > 0) {
                ans.add(sb.toString());
            }
        }

        return ans;
    }
}