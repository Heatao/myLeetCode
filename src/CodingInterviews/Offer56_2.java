package CodingInterviews;

public class Offer56_2 {
    /*
    from krahets
    统计所有数字的各二进制位中 11 的出现次数，并对 33 求余，结果则为只出现一次的数字。
    建立一个长度为 32 的数组 counts，记录所有数字的各二进制位的 1 的出现次数。
    利用 左移操作 和 或运算 ，可将 counts 数组中各二进位的值恢复到数字 resres 上
     */
    public int singleNumber(int[] nums) {
        int[] counts = new int[32];
        for (int num : nums) {
            for(int j = 0; j < 32; j++) {
                counts[j] += num & 1;
                num >>>= 1;
            }
        }
        
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res = res | counts[31-i] % 3;      // 得到 只出现一次的数字 的第 (31 - i) 位
        }

        return res;
    }
}
