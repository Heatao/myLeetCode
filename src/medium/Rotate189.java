package medium;

/**
 * 要求想出尽可能多的解决方案
 * 1. 用一个相同长度的数组，每个数字放到新数组对应的位置上
 * 2. 从0开始，放到下一个位置，然后下一个位置放到下下个位置上，也许可以用递归做，但这样的话算是O(1)吗
 */
public class Rotate189 {
    /**
     * 下面的做法是彻底错误的，因为数组最后的数字会移动到数组开头，并且index<k，这时候很难判断这个数字是否需要再继续递归下去
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k == 0)
            return;
        for (int i = 0; i < k; i++) {
            int nextNum = nums[i];
            recur(nums, k, i, nextNum, false);
        }
    }

    private void recur(int[] nums, int k, int index, int retainNum, boolean lastTime) {
        int nextIndex = (index + k) % nums.length;
        if (index + k >= nums.length)
            lastTime = true;
        int nextRetain = nums[nextIndex];
        nums[nextIndex] = retainNum;
        if (!lastTime) {
            recur(nums, k, nextIndex, nextRetain, false);
        }
    }

    /**
     * 看了官方的题解
     * 交换的方法可以通过计数来判断是否还需要继续进行交换哟～
     */
    public void others1_rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k == 0)
            return;
        int len = nums.length;
        k = k % len;
        int count = 0, i = 0;
        while (count < len) {
            int prevNum = nums[i];
            int nextIndex = (i + k) % len;
            int retainNum = nums[nextIndex];
            int start = nextIndex;                                  // 很关键，判断是否存在环
            // 下面用do-while比较合适，因为设置的是start=nextIndex
            do {
                nums[nextIndex] = prevNum;
                prevNum = retainNum;
                nextIndex = (nextIndex + k) % len;
                retainNum = nums[nextIndex];
                count++;
            }while (count < len && nextIndex != start);
            i++;
        }
    }

    /**
     * 翻转的方法
     * 该方法基于如下的事实：当我们将数组的元素向右移动 k 次后，尾部 k mod n 个元素会移动至数组头部，其余元素向后移动 k mod n 个位置。
     * 该方法为数组的翻转：我们可以先将所有元素翻转，这样尾部的 k mod n 个元素就被移至数组头部，然后我们再翻转 [0, k mod n-1] 区间的元素和 [k mod n, n-1] 区间的元素即能得到最后的答案。
     *
     */
    public void others2_rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k == 0)
            return;
        int len = nums.length;
        k = k % len;
        reverse(nums, 0, len-1);
        reverse(nums, 0, k-1);                      // 这里必须是k-1哟
        reverse(nums, k, len-1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start += 1;
            end -= 1;
        }
    }

    public static void main(String[] args) {
        Rotate189 rotate189 = new Rotate189();
        int[] nums1 = {1,2,3,4,5,6,7};
        int[] nums2 = {-1,-100,3,99};
        int k = 3;
        Rotate189 rotate891 = new Rotate189();
        rotate189.others1_rotate(nums1, k);
        for (int num : nums1) {
            System.out.println(num);
        }
    }
}
