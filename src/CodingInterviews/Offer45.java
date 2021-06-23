package CodingInterviews;

import java.util.Arrays;

public class Offer45 {
    /**
     * 感觉之前做过类似的，应该是求最大的那个，但是由于判断先后顺序不止是9的个数，长度也很重要，有多个决定排序的原因，所以当时没做出来
     * 回过头去看了看lc179题，发现原来只需要两两比较orz
     *
     * 其实快排就可以啦，还可以用内置的排序
     */
    public String minNumber(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                String thisStr1 = nums[i] + "" + nums[j];
                String thisStr2 = nums[j] + "" + nums[i];
                if (Long.parseLong(thisStr1) < Long.parseLong(thisStr2))
                    continue;
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int each : nums) {
            ans.append(each);
        }
        return ans.toString();
    }

    public String others_minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder res = new StringBuilder();
        for(String s : strs)
            res.append(s);
        return res.toString();
    }

    public static void main(String[] args) {
        Offer45 offer45 = new Offer45();
        int[] nums1 = {10,2};
        int[] nums2 = {3,30,34,5,9};
        System.out.println(offer45.minNumber(nums2));
    }
}
