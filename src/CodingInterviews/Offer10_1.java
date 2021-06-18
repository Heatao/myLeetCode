package CodingInterviews;

public class Offer10_1 {
    /**
     * 这道题多亏了先写了psvm的测试
     */
    public int fib(int n) {
        if (n == 0)
            return 0;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Offer10_1 offer10_1 = new Offer10_1();
        System.out.println(offer10_1.fib(2));
    }
}
