package CodingInterviews;

public class Offer66 {
    /*
    如果这道题可以用除法就太简单了，不能用的话属实想不到
    根据krahets的解法，这道题可以将之划分为上三角和下三角
    先乘一部分，再乘另一部分
     */
    public int[] constructArr(int[] a) {
        if(a.length == 0) return new int[0];
        int[] b = new int[a.length];
        b[0] = 1;
        for(int i = 1; i < a.length; i++) {
            b[i] = b[i - 1] * a[i - 1];                     // 把图画出来
        }

        // 下面必须倒着来，同样看图可知，上三角倒着来才会有重复的部分
        int tmp = 1;
        for(int i = a.length - 2; i >= 0; i--) {
            tmp *= a[i + 1];
            b[i] *= tmp;
        }
        return b;
    }
}
