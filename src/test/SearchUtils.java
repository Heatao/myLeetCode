package test;

public final class SearchUtils {
    private SearchUtils() {
    }

    /**
     * 求非降序范围[first, last)内第一个不小于value的值的位置
     * 这里是找下界，下标减一就获得相邻互补的上界
     * @return return last也可以，因为[first, last)为空的时候它们重合
     *
     * 参考二分查找有几种写法？它们的区别是什么？ - Jason Li的回答 - 知乎
     * https://www.zhihu.com/question/36132386/answer/530313852
     */
    public static int bisect_left(int[] nums, int first, int last, int target) {
        int mid;
        while (first < last) {
            mid = first + (last - first) / 2;
            if (nums[mid] < target)
                first = mid + 1;
            else
                last = mid;
        }
        return first;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,2,3,3,4,5,7,8,8,9,10};;
        System.out.println(nums[SearchUtils.bisect_left(nums, 0, nums.length-1, 8)]);
    }
}
