package CodingInterviews;

public class Offer53_1 {
    // 典型的二分查找
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        return bisect(nums, target, 0, nums.length);
    }

    private int bisect(int[] nums, int target, int left, int right) {
        int cmt = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left =  mid + 1;
            }
            else if (nums[mid] > target) {
                right = mid;
            }
            else {
                int l = mid-1;
                int r = mid+1;
                cmt++;
                while (l >= 0 && nums[l] == target) {
                    cmt++;
                    l--;
                }
                while (r <= nums.length-1 && nums[r] == target) {
                    cmt++;
                    r++;
                }
                return cmt;
            }
        }
        return cmt;
    }

    public static void main(String[] args) {
        int[] nums1 = {5,7,7,8,8,10};
        int[] nums2 = {1};
        int target1 = 6;
        int target2 = 1;
        Offer53_1 offer53_1 = new Offer53_1();
        System.out.println(offer53_1.search(nums2, target2));
    }
}
