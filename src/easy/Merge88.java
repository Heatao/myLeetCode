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
        merge88.do2nd(nums1,3,nums2,3);
        System.out.println(Arrays.toString(nums1));
    }

    /**
     * 第二次做，还没有第一次做的好，可悲可叹
     * 还有一种做法是添加到num1后面再排序
     */
    private void do2nd(int[] nums1, int m, int[] nums2, int n) {
        int[] numsAll = new int[m+n];
        int index1 = 0, index2 = 0;
        int i = 0;
        while(index1 < m && index2 < n) {
            if(nums1[index1] < nums2[index2]) {
                numsAll[i] = nums1[index1];
                index1++;
            }
            else {
                numsAll[i] = nums2[index2];
                index2++;
            }
            i++;
        }

        while(index1 < m) {
            numsAll[i] = nums1[index1];
            index1++;
            i++;
        }
        while(index2 < n) {
            numsAll[i] = nums2[index2];
            index2++;
            i++;
        }
        // 参数：源数组，源数组开始位置，目的数组，目的数组开始位置，长度
        System.arraycopy(numsAll, 0, nums1, 0, m+n);
    }
}
