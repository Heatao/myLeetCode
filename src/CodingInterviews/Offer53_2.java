package CodingInterviews;

public class Offer53_2 {
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return bisect(nums, 0, nums.length);            // 左闭右开
    }

    private int bisect(int[] nums, int first, int last) {
        while (first < last) {
            int mid = first + (last - first) / 2;
            if (nums[mid] == mid) {
                first = mid + 1;
            }
            else last = mid;
        }
        return first;
    }

    public static void main(String[] args) {
        Offer53_2 offer53_2 = new Offer53_2();
        int[] nums1 = {0,1,2,3,4,5,6,7,9};
        int[] nums2 = {0,1,3};
        System.out.println(offer53_2.missingNumber(nums2));
    }
}
