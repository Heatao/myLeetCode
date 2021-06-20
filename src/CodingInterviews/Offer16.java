package CodingInterviews;

public class Offer16 {
    public double myPow(double x, int n) {
        if (x == 0 || x == 1) return x;
        if (n == 0) return 1;
        long nn = n;
        if (n < 0) {
            x = 1 / x;
            nn = -nn;
        }
        double ans = 1;
        for (int i = 0; i < nn; i++) {
            ans *= x;
            if (ans == 0) return 0;
        }
        return ans;
    }

    /**
     * 看懂之后忍不住高呼，太精妙了
     * 二进制的角度看问题
     * 和我的做法区别在于这里每次乘的是x的b（b的二进制1的位数）
     */
    public double others_myPow(double x, int n) {
        if(x == 0) return 0;
        long b = n;
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Offer16 offer16 = new Offer16();
        double x = 2.1;
        int n = 3;
        System.out.println(offer16.myPow(x, n));
    }
}
