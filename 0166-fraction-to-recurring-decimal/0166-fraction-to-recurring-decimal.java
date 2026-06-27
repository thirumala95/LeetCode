import java.util.HashMap;
import java.util.Map;

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        // Handle 0 explicitly
        if (numerator == 0) {
            return "0";
        }
        
        StringBuilder res = new StringBuilder();
        
        // Determine the sign
        if ((numerator < 0) ^ (denominator < 0)) {
            res.append("-");
        }
        
        // Convert to long to prevent overflow (e.g., Integer.MIN_VALUE)
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        
        // 1. Integral part
        res.append(num / den);
        long remainder = num % den;
        
        // If it divides perfectly, we are done
        if (remainder == 0) {
            return res.toString();
        }
        
        // 2. Fractional part
        res.append(".");
        
        // Map to store: Key = remainder, Value = current string length (index)
        Map<Long, Integer> map = new HashMap<>();
        
        while (remainder != 0) {
            // If the remainder is already seen, a cycle is detected
            if (map.containsKey(remainder)) {
                int index = map.get(remainder);
                res.insert(index, "(");
                res.append(")");
                break;
            }
            
            // Record the current index position for this remainder
            map.put(remainder, res.length());
            
            // Perform simulated long division
            remainder *= 10;
            res.append(remainder / den);
            remainder %= den;
        }
        
        return res.toString();
    }
}