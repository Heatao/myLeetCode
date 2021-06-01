package easy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsPalindrome125 {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase().replace("_", "");
        Pattern p = Pattern.compile("\\w+");
        Matcher m = p.matcher(s);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            sb.append(m.group());
        }
        String s1 = sb.toString();

        String s2 = new StringBuilder(s1).reverse().toString();
        return s1.equals(s2);
    }

    public static void main(String[] args) {
        IsPalindrome125 isPalindrome125 = new IsPalindrome125();
        String s = "ab_a";
        System.out.println(isPalindrome125.isPalindrome(s));
    }
}
