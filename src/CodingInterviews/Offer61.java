package CodingInterviews;

import java.util.Arrays;

public class Offer61 {
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int cmt0 = 0;
        int prev = -1;
        for (int i = 0; i < 5; i++) {
            if (nums[i] == 0) {
                cmt0 += 1;
                continue;
            }
            if (prev == -1)
                prev = nums[i];
            else {
                if (nums[i] != prev + 1) {
                    while (nums[i] != prev + 1 && cmt0 > 0) {
                        cmt0 -= 1;
                        prev += 1;
                    }
                    if (nums[i] != prev + 1)
                        return false;
                }
                prev = nums[i];
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Offer61 offer61 = new Offer61();
        int[] nums = {9,0,6,0,10};
        System.out.println(offer61.isStraight(nums));
    }
}
