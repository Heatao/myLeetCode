package medium;

/**
 * LeetCode14.最长公共前缀
 * 想到一定会遍历所有的str，但怎么遍历也是可以选择的！
 */
public class longestPrefix {
    public static String mySolution_longestPrefix(String[] strs) {
        if (strs == null || strs.length==0) return "";

        StringBuilder prefix = new StringBuilder();
        char thisChar;
        int tag = 0;
        for (int i = 0; i < strs[0].length(); i++) {
            thisChar = strs[0].charAt(i);
            prefix.append(thisChar);
            for (String str : strs) {
                if (str.length() == i || str.charAt(i) != thisChar) {
                    prefix.deleteCharAt(i);
                    tag = 1;
                    break;
                }
            }
            if (tag == 1) break;
        }
        return prefix.toString();
    }

    public static void main(String[] args) {
        String[] strs = {"aa", "a"};
        System.out.println(mySolution_longestPrefix(strs));
    }
}
