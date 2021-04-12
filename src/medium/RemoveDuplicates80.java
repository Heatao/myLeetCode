package medium;

import java.util.Arrays;

public class RemoveDuplicates80 {
    /*
    提示nums已按升序排列。
    考虑使用双指针～
    双指针好麻烦，重新思考：
    最笨的方法是当出现第三个重复数字就把数组后面部分往前移动一位，但是这样会频繁移动
    所以可以先第一遍计算出长度，第二遍再把合适的数填进去就好了
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 1)
            return 1;
        //计算新长度
        int newLen = 1;
        int tag = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                if (tag == 2) {
                    continue;
                }
                tag++;
            }
            else {
                tag = 1;
            }
            newLen++;
        }

        //移动
        tag = 1;
        int lp = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                if (tag == 2) {
                    continue;
                }
                tag++;
            }
            else {
                tag = 1;
            }
            nums[lp] = nums[i];
            lp++;
        }
//        System.out.println(Arrays.toString(nums));
        return newLen;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,1,2,3,3};
        RemoveDuplicates80 removeDuplicates80 = new RemoveDuplicates80();
        System.out.println(removeDuplicates80.removeDuplicates(nums));
    }
}
