package medium;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 * 说明:
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 给定数独永远是 9x9 形式的。
 */
public class IsValidSudoku36 {
    /**
     * 反思一下：既然题目已经给定了空间不会变，而且只有9*9，其实就是在提醒可以用3*9个hash表啊，即便这样空间也不大
     * 从最笨的方法想起：遍历列，遍历行，再判断每一个子数独
     *
     * 一次遍历的技巧 box_index = (row / 3) * 3 + columns / 3，其中 / 是整数除法。
     * 其实可以用数组而不是HashMap
     * @param board
     * @return
     */
    public boolean mySolution_isValidSudoku(char[][] board) {
        int N = 9;
        HashSet<Character>[] rows = new HashSet[N];
        HashSet<Character>[] cols = new HashSet[N];
        HashSet<Character>[] boxes = new HashSet[N];
        for (int i = 0; i < N; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char key = board[i][j];
                if (key == '.')
                    continue;
                //判断行
                if (!rows[i].contains(key)){
                    rows[i].add(key);
                }
                else return false;

                //判断列
                if (!cols[j].contains(key)){
                    cols[j].add(key);
                }
                else return false;

                //判断box,box这里需要判断第几个,i不对
                int boxId = (i / 3) * 3 + j / 3;
                if (!boxes[boxId].contains(key)){
                    boxes[boxId].add(key);
                }
                else return false;
            }
        }
        return true;
    }

    /**
     * 位运算数组
     * 如何判断row[i] 是否已填tmp数字？
     * rows[i] >> tmp & 1
     * 比如，i = 0， tmp = 5，判断第0行是否填了数字5？
     * rows[0] >> 5 & 1，将row[0] 向右移5位，那么row[0]的二进制形式的第5位处于最低位，此时再跟1进行与操作，就可以获得最低位的数字，也就是原来处于row[0]的二进制形式的第5位的数字。
     * 如果等于1，代表已填过5。此时可以直接return false
     * 来源：https://leetcode-cn.com/problems/valid-sudoku/solution/javawei-yun-suan-1ms-100-li-jie-fang-ge-suo-yin-by/
     */
    public boolean others_isValidSudoku(char[][] board) {
        int N = 9;
        int[] rows = new int[N]; //行的位运算数组
        int[] cols = new int[N]; //列的位运算数组
        int[] boxes = new int[N]; //方格的位运算数组
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == '.')
                    continue;
                int tmp = board[i][j] - '0';
                int boxIndex = i / 3 * 3 + j / 3;
                if ((rows[i] >> tmp & 1) == 1 //rows[i] >> tmp & 1取出第i行的tmp数字，看是否已填，如果等于1，代表已填
                        || (cols[j] >> tmp & 1) == 1 //cols[j] >> tmp & 1取出第j列的tmp数字，看是否已填，如果等于1，代表已填
                        || (boxes[boxIndex] >> tmp & 1) == 1) //boxes[boxIndex] >> tmp & 1取出第boxIndex个方格的tmp数字，看是否已填，如果等于1，代表已填
                    return false;
                rows[i] = rows[i] | (1 << tmp); //将tmp数字加入到第i行的位运算数组
                cols[j] = cols[j] | (1 << tmp); //将tmp数字加入到第j列的位运算数组
                boxes[boxIndex] = boxes[boxIndex] | (1 << tmp); //将tmp数字加入到第boxIndex个方格的位运算数组
            }
        }
        return true;
    }
}
