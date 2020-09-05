package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 *
 * 示例 1:
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 */
public class zConvert {
    public static String mySolution_convert(String s, int numRows) {
        /**
         * 我的思路：用一个二维数据，再用一个tag指向目前这列哪里应该放字符，因为Z字型中间的列只有一个字符
         * 注意点：Character [][] Array = new Character[numRows][len];初始化每个元素为null
         * Java的&&，是先判断左边
         *
         * 二维数组虽然能解开，但是拿到面试不够看
         */
        int len = s.length();
        Character [][] Array = new Character[numRows][len];

        int startIndex = 0;
        int tag = numRows;
        for1:
        for (int j=0; j < len; j++){
            for (int i=0; i < numRows; i++){
                if (startIndex >= len) break for1;
                if (tag == numRows){
                    System.out.println(startIndex);
                    Array[i][j] = s.charAt(startIndex);
                    startIndex ++;
                }
                else if ((i+1) == tag){
                    Array[i][j] = s.charAt(startIndex);
                    startIndex ++;
                }
                else Array[i][j] = '#';
//                System.out.println(Array[i][j] + " " + tag);
            }
            tag -= 1;
            //这里tag必须<=1，因为numRows可能为1
            if (tag <= 1) tag = numRows;
        }

        StringBuilder str = new StringBuilder();
        for (int i=0; i < numRows; i++){
            for (int j=0; j < len; j++){
                //这里必须Array[i][j] != null在左边
                if ( Array[i][j] != null && Array[i][j] != '#' ) str.append(Array[i][j]);
            }
        }
        return str.toString();
    }

    public static String others_convert(String s, int numRows) {
        /**
         * 把之前的二维数组按行存储为字符串s1...sn，则可以发现，按顺序遍历s时，在Z字形的行索引先从s1增大到sn，再减少到s1。。。如此反复
         * 比如：
         *  * L   C   I   R
         *  * E T O E S I I G
         *  * E   D   H   N
         *  1 2 3 2 1 2 3 2 1 ...
         *  所以可以模拟这个行索引的变化，在遍历s中把每个字符填到正确的行res[i]
         *
         *  算法流程：按顺序遍历字符串s
         *  res[i] += c: 把每个字符c填入对应行si
         *  i += flag: 更新当前字符c对应的行索引
         *  flag = -flag: 在达到Z字形转折点时，执行反向
         *
         *  来源：https://leetcode-cn.com/problems/zigzag-conversion/solution/zzi-xing-bian-huan-by-jyd/
         */
        if(numRows < 2) return s;

        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for(int i = 0; i < numRows; i++) rows.add(new StringBuilder());

        int i = 0, flag = -1;
        for(char c : s.toCharArray()) {
            rows.get(i).append(c);

            if(i == 0 || i == numRows -1) flag = - flag;
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder row : rows) res.append(row);
        return res.toString();
    }

    public static void main(String[] args) {
        String str = "AB";
        int numRows = 1;
        System.out.println(mySolution_convert(str, numRows));
    }
}
