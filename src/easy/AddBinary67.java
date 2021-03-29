package easy;

public class AddBinary67 {
    public String addBinary(String a, String b) {
        //可以用最长的，也可以用最短的作为循环的终止，但是长的好，因为会新建字符串
        StringBuffer sb = new StringBuffer();
        int len;
        if (a.length() >= b.length()) {
            len = a.length();
            int temp = a.length()-b.length();
            StringBuilder bBuilder = new StringBuilder(b);
            while (temp > 0) {
                temp--;
                bBuilder.insert(0, '0');
            }
            b = bBuilder.toString();
        }
        else {
            len = b.length();
            int temp = b.length()-a.length();
            StringBuilder aBuilder = new StringBuilder(a);
            while (temp > 0) {
                temp--;
                aBuilder.insert(0, '0');
            }
            a = aBuilder.toString();
        }

        int one = 0;
        for (int i = len-1; i >= 0; i--) {
            one += a.charAt(i) - '0';
            one += b.charAt(i) - '0';
            sb.append((char)(one % 2 + '0'));
            //下面这一步是精髓，保留了下一次进位的部分
            one /= 2;
        }

        //最后可能进一位
        if (one > 0) {
            sb.append('1');
        }

        //下面也是精髓，因为append是后向的，但是加法是从后向前，所以需要反转
        //或者前面可以用sb.insert(0, 字符串)
        sb.reverse();
        return sb.toString();
    }
}
