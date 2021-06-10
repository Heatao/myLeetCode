package easy;

/**
 * 这道题还真没那么低频呐
 */
public class ConvertToTitle168 {
    public String others_convertToTitle(int columnNumber) {
        if (columnNumber <= 0)
            return "";

        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            // 这道题最关键的就是这个--
            // 减了一，则就是从0开始了，0-25对应者'A'-'Z'，就是标准的26进制了，第二轮循环可以看成对一个新的n convertToTitle操作。
            columnNumber--;
            sb.append((char) (columnNumber % 26 + 'A'));
            columnNumber = columnNumber / 26;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(702/26);
        System.out.println(27/16);
        System.out.println(701 % 26);
        System.out.println(702 % 26);
        int n = 701;
        ConvertToTitle168 convertToTitle168 = new ConvertToTitle168();
        System.out.println(convertToTitle168.others_convertToTitle(n));
    }
}
