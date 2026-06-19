class Solution {
    public int magicalString(int n) {
        if (n <= 0) return 0;
        if (n <= 3) return 1;

        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 2;

        int count = 1;
        int i = 2, j = 3, num = 1;

        while (j < n) {
            for (int k = 0; k < arr[i] && j < n; k++) {
                arr[j] = num;

                if (num == 1)
                    count++;

                j++;
            }

            num = (num == 1) ? 2 : 1;
            i++;
        }

        return count;
    }
}