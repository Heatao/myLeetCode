package easy;

public class TrailingZeroes172 {
    /*
    下面是一个错误的做法哟
     */
    public int trailingZeroes(int n) {
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(factorial);
        sb = sb.reverse();

        int count = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '0') count++;
            else break;
        }
        return count;
    }

    // 经评论提醒，数5就完事儿，靠数5还数错了
    public int others_trailingZeroes(int n) {
        int ans = 0;
//        for (int i = 1; i <= n; i++) {
//            if (i % 5 == 0) ans++;
//        }
        while (n >= 5) {
            ans += n / 5;
            n /= 5;
        }
        return ans;
    }

    public static void main(String[] args) {
        TrailingZeroes172 trailingZeroes172 = new TrailingZeroes172();
        int n = 13;
        System.out.println(trailingZeroes172.trailingZeroes(n));
    }
}
