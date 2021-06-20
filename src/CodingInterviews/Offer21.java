package CodingInterviews;

import java.util.Arrays;

public class Offer21 {
    public int[] exchange(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        int first = 0;
        int last = nums.length-1;
        while (first < last) {
            while (first < nums.length && nums[first] % 2 == 1) first++;
            while (last >= 0 && nums[last] % 2 == 0) last--;
            if (first < last) {
                int tmp = nums[first];
                nums[first] = nums[last];
                nums[last] = tmp;
                first++;
                last--;
            }
        }

        return nums;
    }

    public static void main(String[] args) {
        Offer21 offer21 = new Offer21();
        int[] nums = {1,2,5};
        System.out.println(Arrays.toString(offer21.exchange(nums)));
    }
}
