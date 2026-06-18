import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> answer = new ArrayList<>();
        
        // Loop from 1 to n (1-indexed as required)
        for (int i = 1; i <= n; i++) {
            // Condition 1: Divisible by both 3 and 5
            if (i % 3 == 0 && i % 5 == 0) {
                answer.add("FizzBuzz");
            } 
            // Condition 2: Divisible by 3
            else if (i % 3 == 0) {
                answer.add("Fizz");
            } 
            // Condition 3: Divisible by 5
            else if (i % 5 == 0) {
                answer.add("Buzz");
            } 
            // Condition 4: Divisible by neither
            else {
                answer.add(String.valueOf(i));
            }
        }
        
        return answer;
    }
}