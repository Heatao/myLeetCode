package CodingInterviews;

public class Offer14_1 {
    // 我咋感觉切成3的乘积最大呀
    public int cuttingRope(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;

        int maxNum = 1;
        int num3 = 0;
        if (n > 4) num3 = n / 3;

        int elseNum = 1;
        if (n % 3 == 1) {
            num3 -= 1;
            elseNum = 4;
        }
        else if (n % 3 == 2) {
            elseNum = 2;
        }

        for (int i = 0; i < num3; i++) {
            maxNum *= 3;
        }
        maxNum *= elseNum;
        return maxNum;
    }

    /**
     * 他人写的，很简洁呀
     */
    public int others_cuttingRope(int n) {
        if(n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if(b == 0) return (int)Math.pow(3, a);
        if(b == 1) return (int)Math.pow(3, a - 1) * 4;
        return (int)Math.pow(3, a) * 2;
    }

    public static void main(String[] args) {
        Offer14_1 offer14_1 = new Offer14_1();
        System.out.println(offer14_1.cuttingRope(3));
    }
}
