package CodingInterviews;

/**
 * 我觉得可以找规律做，不过找完规律花的时间可不少呐
 * 看了题解发现是按位进行DP
 */
public class Offer43 {
    // 这里高位是指前面的所有，而不是只高10哟
    public int countDigitOne(int n) {
        int digit = 1, res = 0;
        // 一开始从cur % 10开始，下面如下初始化
        int high = n / 10;
        int cur = n % 10;
        int low = 0;
        while (high != 0 || cur != 0) {
            switch (cur) {
                case 0 -> res += high * digit;
                case 1 -> res += high * digit + low + 1;
                default -> res += (high + 1) * digit;
            }
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }
}
