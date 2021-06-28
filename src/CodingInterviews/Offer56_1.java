package CodingInterviews;

/**
 * 没做出来，看了答案知道是采用位运算，这种数组中数字出现的题目大概率是位运算
 */
public class Offer56_1 {
    /**
     * 官方题解的思路：
     * 通过异或将数组分成两组，分别异或
     */
    public int[] singleNumbers(int[] nums) {
        int group1 = 0, group2 = 0, ret = 0;
        for (int num : nums) {
            ret ^= num;
        }

        int div = 1;
        // 判断第几位用的是&，&是相同1才是1，其余为0
        // 异或^是相同是0，不同才是1
        while ((div & ret) == 0) {
            div  = div << 1;
        }
        for (int num : nums) {
            // 出现两次的数字和div与会分到同一组去
            if ((div & num) == 0) group1 = group1 ^ num;
            else group2 = group2 ^ num;
        }

        return new int[]{group1, group2};
    }
}
