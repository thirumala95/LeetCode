class Solution {
    public int[] dailyTemperatures(int[] temps) {
        int[] days = new int[temps.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < temps.length; i++) {
            while (!stack.isEmpty() && temps[i] > temps[stack.peek()]) {
                int idx = stack.pop();
                days[idx] = i - idx;
            }
            stack.push(i);
        }
        return days;
    }
}