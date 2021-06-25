package CodingInterviews;

public class Offer51 {
    /**
     * 看题意感觉是直接双层循环，是我太naive了嘛
     * 是的，这样直接超时了呢，也就是不能o(n^2)的时间复杂度
     * 其实这一点可以从题目所给的数组长度看出，数组长度50000，一般就不允许用O(n^2)的时间了
     */
    public int reversePairs(int[] nums) {
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) cnt++;
            }
        }
        return cnt;
    }

    /**
     * 题解提示用归并排序
     */
    int[] tmp;
    int revers;
    public int reversePairs_merge(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int len = nums.length;
        tmp = new int[len];
        revers = 0;

        merge_sort(nums, 0, len-1);
        return revers;
    }

    private void merge_sort(int[] nums, int first, int last) {
        if (first >= last) return;
        int mid = first + (last - first) / 2;
        merge_sort(nums, first, mid);
        merge_sort(nums, mid+1, last);

        merge(nums, first, mid, last);
    }

    private void merge(int[] nums, int first, int mid, int last) {
        int index = first;
        int leftIndex = first;
        int rightIndex = mid + 1;
        while (leftIndex <= mid && rightIndex <= last) {
            if (nums[leftIndex] <= nums[rightIndex])
                tmp[index++] = nums[leftIndex++];
            else {
                revers += mid - leftIndex + 1;              // 这里用来统计逆序对
                tmp[index++] = nums[rightIndex++];
            }
        }

        while (leftIndex <= mid) {
            tmp[index++] = nums[leftIndex++];
        }
        while (rightIndex <= last) {
            tmp[index++] = nums[rightIndex++];
        }

        index = first;
        while (index <= last) {
            nums[index] = tmp[index];
            index++;
        }
    }
}
