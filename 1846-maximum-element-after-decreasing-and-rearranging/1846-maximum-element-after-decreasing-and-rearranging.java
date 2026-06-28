import java.util.Arrays;

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        // Step 1: Sort the array to process elements in ascending order
        Arrays.sort(arr);
        
        // Step 2: The first element must always be 1
        arr[0] = 1;
        
        // Step 3: Iterate through the array and enforce the maximum allowed step up
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] > 1) {
                arr[i] = arr[i - 1] + 1;
            }
        }
        
        // The last element will represent the maximum possible value achieved
        return arr[arr.length - 1];
    }
}