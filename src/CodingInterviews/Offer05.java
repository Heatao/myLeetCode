package CodingInterviews;

public class Offer05 {
    /**
     * 注意可能有多个空格连在一起喔，所以下面的写法是错误的
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        String[] s_list = s.split(" ");
        for (int i = 0; i < s_list.length - 1; i++) {
            sb.append(s_list[i]);
            sb.append("%20");
        }
        sb.append(s_list[s_list.length-1]);
        return sb.toString();
    }

    /**
     * Java的字符串是不可变的，所以不能原地修改喔
     */
    public String replaceSpace2(String s) {
        if (s == null || s.length() == 0)
            return s;
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char thisChar = s.charAt(i);
            if (thisChar == ' ')
                sb.append("%20");
            else sb.append(thisChar);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "   ";
        Offer05 offer05 = new Offer05();
        System.out.println(offer05.replaceSpace2(s));
    }
}
