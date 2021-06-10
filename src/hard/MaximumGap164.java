package hard;

import java.util.Arrays;

/**
 * 数组中所有元素都是非负整数，尝试用线性时间复杂度和空间复杂度做
 * 线性时间复杂度的话就不能排序了，不会做
 */
public class MaximumGap164 {
    public int maximumGap(int[] nums) {
        int len = nums.length;
        if (len < 2)
            return 0;
        quickSort(nums, 0, len - 1);

        int ans = 0;
        for (int i = len - 1; i > 0; i--) {
            ans = Math.max(ans, nums[i] - nums[i-1]);
        }
        return ans;
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end)
            return;
        int mid = partition(nums, start, end);

        // mid排好位置之后不需要再传入下一层的递归中
        quickSort(nums, start, mid - 1);
        quickSort(nums, mid + 1, end);
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        while (start < end) {
            while (nums[end] >= pivot && start < end) end--;
            nums[start] = nums[end];

            // 下面 < 或者 <= 影响不大
            while (nums[start] < pivot && start < end) start++;
            nums[end] = nums[start];
        }
        nums[start] = pivot;
        return start;
    }

    /**
     * 基于桶排序的算法，很精妙！
     */
    public int official_maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        int minVal = Arrays.stream(nums).min().getAsInt();
        int maxVal = Arrays.stream(nums).max().getAsInt();
        int d = Math.max(1, (maxVal - minVal) / (n - 1));
        int bucketSize = (maxVal - minVal) / d + 1;

        int[][] bucket = new int[bucketSize][2];
        for (int i = 0; i < bucketSize; ++i) {
            Arrays.fill(bucket[i], -1); // 存储 (桶内最小值，桶内最大值) 对， (-1, -1) 表示该桶是空的
        }
        for (int i = 0; i < n; i++) {
            int idx = (nums[i] - minVal) / d;
            if (bucket[idx][0] == -1) {
                bucket[idx][0] = bucket[idx][1] = nums[i];
            } else {
                bucket[idx][0] = Math.min(bucket[idx][0], nums[i]);
                bucket[idx][1] = Math.max(bucket[idx][1], nums[i]);
            }
        }

        int ret = 0;
        int prev = -1;
        for (int i = 0; i < bucketSize; i++) {
            if (bucket[i][0] == -1) {
                continue;
            }
            if (prev != -1) {
                ret = Math.max(ret, bucket[i][0] - bucket[prev][1]);
            }
            prev = i;
        }
        return ret;
    }

    public static void main(String[] args) {
        MaximumGap164 maximumGap164 = new MaximumGap164();
        int[] nums = {3,6,9,1};
        System.out.println(maximumGap164.maximumGap(nums));
    }
}
