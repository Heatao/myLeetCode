package CodingInterviews;

public class Offer46 {
    // 下面的做法是错误的，虽然想到了DP，但是我这样写根本就是错误的呀
    public int translateNum(int num) {
        int ans = 1;
        String numStr = String.valueOf(num);
        for (int i = 1; i < numStr.length(); i++) {
            char curChar = numStr.charAt(i);
            if (curChar - '0' <= 5 && numStr.charAt(i-1) == '1' || numStr.charAt(i-1) == '2'
                    || (curChar - '0' == 6 && numStr.charAt(i-1) == '1')) {
                ans += 1;
            }
            // ans += 1;
        }
        return ans;
    }

    // 下面和上面是一样的
    public int translateNum2(int num) {
        String numStr = String.valueOf(num);
        int len = numStr.length();
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            int curChar = numStr.charAt(i) - '0';
            if ((curChar <= 5 && numStr.charAt(i-1) == '1' || numStr.charAt(i-1) == '2')
                    || (curChar == 6 && numStr.charAt(i-1) == '1'))
                dp[i] = dp[i-1] + 1;
            else dp[i] = dp[i-1];
        }
        return dp[len-1];
    }

    // 这个题要理解为青蛙跳台子的
    public int translateNum3(int num) {
        String numStr = String.valueOf(num);
        int len = numStr.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= len; i++) {
            int curChar = numStr.charAt(i-1) - '0';
            if ((curChar <= 5 && numStr.charAt(i-2) == '2')
                    ||  numStr.charAt(i-2) == '1')
                dp[i] = dp[i-1] + dp[i-2];
            else dp[i] = dp[i-1];
        }
        return dp[len];
    }

    public static void main(String[] args) {
        Offer46 offer46 = new Offer46();
        int num1 = 18580;
        int num2 = 26;
        System.out.println(offer46.translateNum3(num2));
    }
}
