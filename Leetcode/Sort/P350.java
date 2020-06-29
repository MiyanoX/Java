import java.util.Arrays;

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int s1 = 0, s2 = 0, s = 0;
        while (s1 < nums1.length && s2 < nums2.length) {
            if (nums1[s1] > nums2[s2]) {
                s2++;
            } else if (nums1[s1] < nums2[s2]) {
                s1++;
            } else {
                nums1[s] = nums2[s2];
                s++;
                s1++;
                s2++;
            }
        }
        return Arrays.copyOfRange(nums1, 0, s);
    }
}

