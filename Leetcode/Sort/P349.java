import java.util.Arrays;
import java.util.HashSet;

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        HashSet<Integer> s = new HashSet<>();
        int s1 = 0, s2 = 0;
        while (s1 < nums1.length && s2 < nums2.length) {
            if (nums1[s1] > nums2[s2]) {
                s2++;
            } else if (nums1[s1] < nums2[s2]) {
                s1++;
            } else {
                s.add(nums1[s1]);
                s1++;
                s2++;
            }
        }
        int[] res = new int[s.size()];
        int j = 0;
        for (int i : s) {
            res[j] = i;
            j++;
        }
        return res;
    }
}
