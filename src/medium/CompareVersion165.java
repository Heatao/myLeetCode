package medium;

public class CompareVersion165 {
    public int compareVersion(String version1, String version2) {
        // 在正则表达式中，.表示任意一个字符，如果想匹配点.作为符号的话，需要转义\\
        String[] ver1 = version1.split("\\.");
        String[] ver2 = version2.split("\\.");
        int index1 = 0, index2 = 0;
        while (index1 < ver1.length && index2 < ver2.length) {
            if (Integer.parseInt(ver1[index1]) < Integer.parseInt(ver2[index2])) return -1;
            else if (Integer.parseInt(ver1[index1]) > Integer.parseInt(ver2[index2])) return 1;
            index1++;
            index2++;
        }

        // 多余的部分可能是0
        while (index1 < ver1.length) {
            if (Integer.parseInt(ver1[index1]) == 0) index1++;
            else return 1;
        }
        while (index2 < ver2.length) {
            if (Integer.parseInt(ver2[index2]) == 0) index2++;
            else return -1;
        }
        return 0;
    }

    public static void main(String[] args) {
        String s1 = "1.0";
        String s2 = "1.0.1";
        CompareVersion165 compareVersion165 = new CompareVersion165();
        System.out.println(compareVersion165.compareVersion(s1, s2));
    }
}
