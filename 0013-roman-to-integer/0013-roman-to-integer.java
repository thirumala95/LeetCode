class Solution {
    public int romanToInt(String s) {
        int total = 0;   
        int prev = 0;   

  
        for (int i = s.length() - 1; i >= 0; i--) {
            int value = 0;
            char ch = s.charAt(i);

         
            if (ch == 'I') value = 1;
            else if (ch == 'V') value = 5;
            else if (ch == 'X') value = 10;
            else if (ch == 'L') value = 50;
            else if (ch == 'C') value = 100;
            else if (ch == 'D') value = 500;
            else if (ch == 'M') value = 1000;

 
            if (value < prev)
                total -= value;
            else
                total += value;

            prev = value;  
        }
        return total;
    }
}
