package medium;

import java.util.Arrays;

public class SortColors75 {
    /*
    单指针的做法，需要遍历两次
     */
    public void sortColors(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        //这里从0开始，而不是从遍历到0的下一位，这样会使代码简洁，也不需要频繁判断ptr是否指向了0，因为即便ptr指向0，这个0也会被换到前面去
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
        for (int i = ptr; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
    }

    /*
    双指针做法
     */
    public void sortColors1(int[] nums) {
        int p0 = 0;
        int p2 = nums.length-1;
        for (int i = 0; i <= p2; i++) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                p0++;
            }
            if (nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                p2--;
                //精华之处，因为被换出来的可能是2或者0，这样不会再考虑i了，错！
                if (nums[i] != 1)
                    i--;                //不用担心这里越界，因为即便是0，马上就i++了
            }
        }
    }

    public static void main(String[] args) {
        SortColors75 sortColors75 = new SortColors75();
        int[] nums = {0,1,2,0,2,0};
        sortColors75.sortColors(nums);
        System.out.println(Arrays.toString(nums));

        int[] nums1 = {1,2,0};
        sortColors75.sortColors1(nums1);
        System.out.println(Arrays.toString(nums1));
    }
}
