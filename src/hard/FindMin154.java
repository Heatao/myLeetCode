package hard;

/**
 * 存在重复数字
 */
public class FindMin154 {
    public int findMin(int[] nums) {
        int first = 0;
        int last = nums.length-1;
        while (first < last) {
            int mid = first + (last - first) / 2;
            // mid >= last的值，可能是mid和last转过了，也可能是[1,3,3]这种情况
            if (nums[mid] < nums[last])
                last = mid;
            else if (nums[mid] > nums[last])
                first = mid + 1;
            // 这里是解决[1,3,3]这样情况
            else last --;
        }

        return nums[first];
    }

    public static void main(String[] args) {
        FindMin154 findMin154 = new FindMin154();
        int[] nums = {2,2,2,0,1};
        System.out.println(findMin154.findMin(nums));
    }
}
