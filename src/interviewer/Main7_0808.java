package interviewer;

import java.util.Scanner;

public class Main7_0808 {
    public static void main(String[] args) {
        // 顺序的一排不是从小到大的喔
        // 用双指针
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int t = in.nextInt();
        int c = in.nextInt();
        int[] candy = new int[n];
        for (int i = 0; i < n; i++) {
            candy[i] = in.nextInt();
        }

        int ans = 0;
        int left = 0, right = 0;
        for (int i = 0; i < n; i++) {
            while (i < n && candy[i] > t) i++;
            left = i;
            right = i;
            while (right+1 < n && candy[right+1] <= t) {
                right++;
            }
            if (right - left + 1 >= c)                  // 很重要
                ans += right - left + 2 - c;
            // 下面是真的吗
            i = right+1;          // 马上还会i++
        }
//        int i = 0;
//        while (i < n) {
//            while (i < n && candy[i] > t) i++;
//            left = i;
//            right = i;
//            while (right+1 < n && candy[right+1] <= t) {
//                right++;
//            }
//            ans += right - left + 2 - c;
//            i = right+1;
//        }
        System.out.println(ans);
    }

}
