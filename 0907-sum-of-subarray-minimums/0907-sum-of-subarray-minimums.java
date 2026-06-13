class Solution {

    public int sumSubarrayMins(int[] arr) {

        int n = arr.length;
        long MOD = 1000000007;

        int[] left = new int[n];
        int[] right = new int[n];

        Stack<Integer> stack = new Stack<>();

        // Previous Smaller
        for(int i = 0; i < n; i++) {

            while(!stack.isEmpty() &&
                  arr[stack.peek()] > arr[i]) {
                stack.pop();
            }

            left[i] =
                stack.isEmpty()
                ? i + 1
                : i - stack.peek();

            stack.push(i);
        }

        stack.clear();

        // Next Smaller
        for(int i = n - 1; i >= 0; i--) {

            while(!stack.isEmpty() &&
                  arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            right[i] =
                stack.isEmpty()
                ? n - i
                : stack.peek() - i;

            stack.push(i);
        }

        long ans = 0;

        for(int i = 0; i < n; i++) {

            long contribution =
                (long)arr[i]
                * left[i]
                * right[i];

            ans =
                (ans + contribution)
                % MOD;
        }

        return (int)ans;
    }
}