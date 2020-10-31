package easy;

/**
 * LeetCode38.外观数组
 * 给定一个正整数 n ，输出外观数列的第 n 项。
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
 * 你可以将其视作是由递归公式定义的数字字符串序列：
 * countAndSay(1) = "1"
 * countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
 *
 * 提示：
 * 1 <= n <= 30
 */
public class CountAndSay38 {
    public String mySolution_countAndSay(int n) {
        String str = "1";
        for (int i = 1; i < n; i++) {
            str = countAndSayHelper(str);
        }
        return str;
    }

    private String countAndSayHelper(String sawStr) {
        StringBuilder helpedStr = new StringBuilder();

        char tmpChar = sawStr.charAt(0);
        int tmpNum = 1;
        for (int i = 1; i < sawStr.length(); i++) {
            if (tmpChar == sawStr.charAt(i))
                tmpNum++;
            else {
                helpedStr.append(tmpNum);
                helpedStr.append(tmpChar);
                tmpNum = 1;
                tmpChar = sawStr.charAt(i);
            }
        }
        helpedStr.append(tmpNum);
        helpedStr.append(tmpChar);
        return helpedStr.toString();
    }
}
