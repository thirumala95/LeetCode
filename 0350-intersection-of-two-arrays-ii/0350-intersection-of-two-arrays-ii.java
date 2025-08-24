class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int size = Math.min(nums1.length, nums2.length);
        int[] temp = new int[size];
        int count = 0;

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    temp[count++] = nums1[i];
                    nums2[j] = -1; // mark as used
                    break;
                }
            }
        }

        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = temp[i];
        }
        return result;
    }
}
