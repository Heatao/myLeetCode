package CodingInterviews;

public class Offer14_2 {
    // 这个题和上一题的区别在于可能越界
    // 需要看看n的范围喔，上一题n最大58，这道题n最大1000
    public int cuttingRope(int n) {
        if (n <= 3) return n-1;

        // 这里一定要设置为long，因为中间会溢出
        long maxNum = 1;
        while (n > 4) {
            maxNum *= 3;
            maxNum %= 1000000007;
            n -= 3;
        }

        maxNum *= n;
        maxNum %= 1000000007;
        return (int) maxNum;
    }

    public static void main(String[] args) {
        int n = 120;
        Offer14_2 offer14_2 = new Offer14_2();
        System.out.println(offer14_2.cuttingRope(n));
    }
}
