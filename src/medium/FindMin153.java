package medium;

public class FindMin153 {
    public int findMin(int[] nums) {
        return bisect(nums, 0, nums.length-1);
    }

    private int bisect(int[] nums, int left, int right) {
//        if (right - left <= 1)
//            return nums[left];
        if (right - left == 0)
            return nums[left];

        int mid = left + (right - left) / 2;
//        if (nums[mid] > nums[left]) {
//            return bisect(nums, mid+1, right);
//        }
//        else return bisect(nums, left, mid);
        if (nums[mid] < nums[right]) {
            return bisect(nums, left, mid);
        }
        else return bisect(nums, mid+1, right);
    }

    public static void main(String[] args) {
        FindMin153 findMin153 = new FindMin153();
        int[] nums1 = {3,4,5,1,2};
        int[] nums2 = {4,5,6,7,0,1,2};
        int[] nums3 = {11,13,15,17};
        System.out.println(findMin153.findMin(nums2));
    }

    /**
     * 试验一下非递归的，会报错hhhh
     */
    public int noRecur_findMin(int[] nums) {
        int first = 0;
        int last = nums.length;
        while (first < last) {
            int mid = first + (last - first) / 2;
            if (nums[mid] < nums[last])
                first = mid + 1;
            else last = mid;
        }
        return nums[first];
    }
}
