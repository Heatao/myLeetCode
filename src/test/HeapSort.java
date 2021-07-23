package test;

/**
 * 优先队列可以用堆来实现
 * 二叉堆是一个完全二叉树，完全二叉树可以用数组来表示，2n和2n+1表示左右孩子
 * 建堆可以通过上浮和下沉操作。上浮是从叶子节点开始，和其父节点比较和交换。下沉是从根节点开始，和其子节点比较和交换。
 * 删除堆顶，也就是将堆顶与最后一个元素交换，然后再将新的堆顶下沉。
 * 建堆的时间复杂度是O(n)，排序过程时间复杂度为O(nlogn)
 *
 * 比较快速排序和堆排序
 * 1.对于快速排序来说，数据是顺序访问的。而对于堆排序来说，数据是跳着访问的。这样对 CPU 缓存是不友好的
 * 2.相同的的数据，排序过程中，堆排序的数据交换次数要多于快速排序。
 *
 */
public class HeapSort {
    public int[] heapSort(int[] nums) {

        int len = nums.length;
        int[] a = new int[len + 1];

        for (int i = 0; i < nums.length; ++i) {
            a[i+1] = nums[i];
        }
        //下沉建堆
        for (int i = len/2; i >= 1; --i) {
            sink(a,i,len);
        }

        int k = len;
        //排序
        while (k > 1) {
            swap(a,1,k--);
            sink(a,1,k);
        }
        for (int i = 1; i < len+1; ++i) {
            nums[i-1] = a[i];
        }
        return nums;
    }
    public void sink (int[] nums, int k,int end) {
        //下沉
        while (2 * k <= end) {
            int j = 2 * k;
            //找出子节点中最大或最小的那个
            if (j + 1 <= end && nums[j + 1] > nums[j]) {
                j++;
            }
            if (nums[j] > nums[k]) {
                swap(nums, j, k);
            } else {
                break;
            }
            k = j;
        }
    }
    public void swap (int nums[], int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
