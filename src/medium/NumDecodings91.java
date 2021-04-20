package medium;

/**
 * 这个题下意识的
 */
public class NumDecodings91 {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0')
            return 0;
        int n = s.length();
        int[] dp = new int[n+1];           //length+1是爬楼梯的小技巧
        dp[0] = 1;
        dp[1] = 1;
        //从2开始，但是对应的是s的第二个字符
        for (int i = 2; i <= n; i++) {
            char thisChar = s.charAt(i-1);                      //这里本应是i，因为下标从0开始，所以-1
            char lastChar = s.charAt(i-2);
            if (thisChar != '0'){
                if (lastChar == '1' || (lastChar == '2' && thisChar - '0' <= 6))
                    dp[i] =  dp[i-1] + dp[i-2];
                else dp[i] = dp[i-1];
            }
            else {
                if (lastChar == '1' || lastChar == '2')
                    dp[i] = dp[i-2];
                else
                    return 0;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        NumDecodings91 numDecodings91 = new NumDecodings91();
        String s = "2611055971756562";
        System.out.println(numDecodings91.numDecodings(s));
    }
}
