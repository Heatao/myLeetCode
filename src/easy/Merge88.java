package easy;

import java.util.Arrays;

public class Merge88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1copy = Arrays.copyOf(nums1, m);
        int p1 = 0, p2 = 0, i = 0;
        while (p1 < m && p2 < n) {
            if (nums1copy[p1] <= nums2[p2]) {
                nums1[i] = nums1copy[p1];
                p1++;
            }
            else {
                nums1[i] = nums2[p2];
                p2++;
            }
            i++;
        }
        if (p1 < m) {
            System.arraycopy(nums1copy, p1, nums1, i, m-p1);
        }
        else {
            System.arraycopy(nums2, p2, nums1, i, n-p2);
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        Merge88 merge88 = new Merge88();
        merge88.merge(nums1,3,nums2,3);
        System.out.println(Arrays.toString(nums1));
    }
}
