package CodingInterviews;

import java.util.Arrays;

public class Offer40 {
    // 显然应该直接排序，这道题考的是快排吧
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] ans = new int[k];
        if (k == 0 || arr == null || arr.length == 0) return ans;
        quickSort(arr, 0, arr.length-1);
        if (k >= 0) System.arraycopy(arr, 0, ans, 0, k);
        return ans;
    }

    public void quickSort(int[] arr, int start, int end) {
        if (start >= end) return;
        int pivot = partition(arr, start, end);
        quickSort(arr, start, pivot-1);
        quickSort(arr, pivot+1, end);           // 这里分别是-1和+1喔
    }

    private int partition(int[] arr, int start, int end) {
        int pivot = arr[start];         // 保留一开始的元素
        while (start < end) {
            while (arr[end] > pivot && start < end) end--;
            arr[start] = arr[end];
            while (arr[start] <= pivot && start < end) start++;
            arr[end] = arr[start];
        }
        arr[start] = pivot;
        return start;
    }

    public static void main(String[] args) {
        Offer40 offer40 = new Offer40();
        int[] arr = {0,1,2,1};
        int k = 2;
        System.out.println(Arrays.toString(offer40.getLeastNumbers(arr, k)));
    }
}
