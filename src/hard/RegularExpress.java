package hard;

/**
 * LeetCode10.正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 */
public class RegularExpress {

    /**
     * 错误的代码：我以为.*中的.只能变成一个字符，也就是.*和ab不匹配，没想到是匹配的
     * @param s
     * @param p
     * @return
     */
    public static boolean mySolution_isMatch(String s, String p) {
        if (p.length()==0 && s.length()!=0) return false;
        if (p.length() != 0 && p.charAt(0) == '*') return false;

        //设置两个指针，代表s和p当前的位置
        int ps = 0;
        int pp = 0;
        while (ps < s.length()) {
            //如果有两个*连在一起，我觉得是错的
            if (pp == p.length()) return false;

            // 如果相等，则，ps和pp前进一位
            // 或者pp的下一位是*，此时查看ps的后一位是否是一样的，ps跳到下一个不一样的地方，pp跳到*的下一位
            if (s.charAt(ps) == p.charAt(pp) || p.charAt(pp) == '.') {
                if (pp+1 != p.length() && ps+1 != s.length() && s.charAt(ps) == s.charAt(ps+1) && p.charAt(pp+1) == '*'){
                    int i=ps;
                    while (s.charAt(i) == s.charAt(ps)){
                        i++;
                        if (i == s.length()){
                            i += 1;
                            break;
                        }
                    }
                    ps = i;
                    pp += 2;
                    if (ps == s.length()) break;
                }
                else if (pp+1 != p.length() && p.charAt(pp+1) == '*'){
                    ps += 1;
                    pp += 2;
                }
                else {
                    ps += 1;
                    pp += 1;
                }
            }
            else if (p.charAt(pp+1) == '*'){
                ps += 1;
                pp += 2;
            }
            else return false;
        }
        //这是p可能还未结束，应该判断是否存在剩余的
        if (pp < p.length()) return false;

        return true;
    }

    /**
     * 我的理解：DP需要满足，求解的结构类似，是一个递归的过程，并且当前的决策需要用到之前的决策。DP优点在于空间换时间，避免的重复搜索的过程。
     * 经典DP：字符串匹配，搜索问题，决策问题。
     * @param s
     * @param p
     * @return
     */
    public static Boolean official_isMatch(String s, String p){
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                }
                else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    public static void main(String[] args) {
        String s = "ab";
        String p = ".*";
        System.out.println(mySolution_isMatch(s, p));
    }
}
