class Solution {
    public int findGCD(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i : nums){
            max = Math.max(max, i);
            min = Math.min(min, i);
        }

        for(int i = min; i > 0; i--){
            if(min % i == 0 && max % i == 0)
                return i;
        }

        return 1;
    }
}