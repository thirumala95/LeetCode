class Solution {

    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    int mergeSort(int[] a, int left, int right) {
        if (left >= right) return 0;

        int mid = (left + right) / 2;
        int count = 0;

        count += mergeSort(a, left, mid);
        count += mergeSort(a, mid + 1, right);
        count += countPairs(a, left, mid, right);

        merge(a, left, mid, right);
        return count;
    }

    int countPairs(int[] a, int left, int mid, int right) {
        int count = 0;
        int j = mid + 1;

        for (int i = left; i <= mid; i++) {
            while (j <= right && (long)a[i] > 2L * a[j]) {
                j++;
            }
            count += (j - (mid + 1));
        }
        return count;
    }

    void merge(int[] a, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) temp[k++] = a[i++];
            else temp[k++] = a[j++];
        }

        while (i <= mid) temp[k++] = a[i++];
        while (j <= right) temp[k++] = a[j++];

        for (int x = 0; x < temp.length; x++) {
            a[left + x] = temp[x];
        }
    }
}
