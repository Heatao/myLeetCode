package easy;

/**
 * LeetCode9.回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 * 输入: 121
 * 输出: true
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 你能不将整数转为字符串来解决这个问题吗？
 */
public class isPalindrome {
    public static boolean mySolution_isPalindrome(int x) {
        /**
         * 我的思路：题目没有限制整数大小，但是代码写的是int，也就限定了范围
         * 如果不用回文的话，就用第二个数吧
         */
        if ((x%10==0 && x!=0) || x<0) return false;
        int y = 0;
        int temp = x;

        while (temp != 0){
//            System.out.println("temp%10 "+temp%10);
            y = y*10 + temp%10;
            temp /= 10;
//            System.out.println("temp "+temp);
        }
        System.out.println(x);
        System.out.println(y);
        return x == y;
    }

    public boolean official_isPalindrome(int x){
        /**
         * 优化：只比较一半的数字
         */
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }

    public static void main(String[] args) {
        int x = 11201;
        System.out.println(mySolution_isPalindrome(x));
    }
}
