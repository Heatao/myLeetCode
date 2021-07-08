package medium;

/**
 * LeetCode33.搜索旋转排序数组
 * 给你一个升序排列的整数数组 nums ，和一个整数 target 。
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。（例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] ）。
 * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 提示：
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * nums 肯定会在某个点上旋转
 * -10^4 <= target <= 10^4
 */
public class SearchRollArray33 {
    /**
     * 第一反应是用折半搜索，递归调用自己，折半的两半分别检查两端是否是升序
     * 肯定不能用O(n)的方法，需要用O(logn)
     */
    public static int search(int[] nums, int target) {
        int aimIndex;
        aimIndex = divSearch(nums, 0, nums.length-1, target);
        return aimIndex;
    }

    public static int divSearch(int[] nums, int start, int end, int target) {
        //跳出递归的条件
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        if (end - start <= 1) return -1;

        if (nums[start] < target && target < nums[end]) {
            int medium = (start + end) / 2;
            if (target <= nums[medium]){
                return divSearch(nums, start, medium, target);
            }
            else {
                return divSearch(nums, medium+1, end, target);
            }
        }
        else if (nums[start] < nums[end]){
            return -1;
        }
        else {
            //目前数组已经是被旋转的，取中间数分两半
            int medium = (start+end) / 2;
            if (nums[start] < nums[medium]) {
                //左半是升序
                if (nums[start] <= target && target <= nums[medium])
                    return divSearch(nums, start, medium, target);
                else
                    return divSearch(nums, medium+1, end, target);
            }
            else {
                //右半是升序
                if (nums[medium] <= target && target <= nums[end])
                    return divSearch(nums, medium, end, target);
                else
                    return divSearch(nums, start, medium-1, target);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int[] nums1 = {1};
        int[] nums2 = {0};
        int[] nums3 = {4,5,6,7,8,1,2};
        int[] nums4 = {1, 3};
        // System.out.println(search(nums, 0));
        SearchRollArray33 searchRollArray33 = new SearchRollArray33();
        System.out.println(searchRollArray33.do2nd(nums, 0));
        System.out.println(searchRollArray33.do2nd(nums1, 0));
        System.out.println(searchRollArray33.do2nd(nums2, 0));
        System.out.println(searchRollArray33.do2nd(nums3, 0));
        System.out.println(searchRollArray33.do2nd(nums4, 3));
        System.out.println(searchRollArray33.do2nd(nums4, 2));
    }

    private int do2nd(int[] nums, int target) {
        return bisectSearch(nums, target, 0, nums.length-1);
    }

    private int bisectSearch(int[] nums,int target, int left, int right) {
        while(left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)                        // 加在这里防止无限循环，因为这个不是标准的bisect，所以下面的if更新可能不会变
                return mid;
            if(nums[mid] < nums[right]) {
                // 右边有顺序
                if(nums[mid] < target && target <= nums[right]) {
                    left = mid+1;                           // 引入了中间判断之后，这里就可以同时加和减了
                }
                else right = mid-1;
            }
            else {
                // 左边有顺序
                if(nums[left] <= target && target < nums[mid]) {
                    right = mid-1;
                }
                else left = mid+1;
            }
        }
        if(nums[left] == target) return left;
        else return -1;
    }
}
