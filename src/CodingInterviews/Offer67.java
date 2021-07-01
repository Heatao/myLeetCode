package CodingInterviews;

/**
 * 第二次做，终于不像第一次做那么憨了，但是仍然忽略了溢出的情况，学习了krahets的做法，Integer.MAX_VALUE / 10来判断
 */
public class Offer67 {
    public int strToInt(String str) {
        if (str == null || str.length() == 0) return 0;
        str = str.trim();
        int num = 0;
        int symble = 1;
        if (str.length() != 0) {
            if (str.charAt(0) == '+')
                symble = 1;
            else if (str.charAt(0) == '-')
                symble = -1;
            else if (str.charAt(0) - '0' <= 9 && str.charAt(0) - '0' >= 0) {
                num = str.charAt(0) - '0';
            }
            else return 0;
        }
        for (int i = 1; i < str.length(); i++) {
            int tmp = str.charAt(i) - '0';
            if (tmp >= 0 && tmp <= 9) {
                if (num > Integer.MAX_VALUE / 10 || num == Integer.MAX_VALUE / 10 && tmp > 7)
                    return symble == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                num = num*10 + tmp;
            }
            else return symble*num;
        }

        return symble*num;
    }

    public static void main(String[] args) {
        Offer67 offer67 = new Offer67();
        String str = "-91283472332";
        System.out.println(offer67.strToInt(str));
    }
}
