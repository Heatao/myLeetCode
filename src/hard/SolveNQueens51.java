package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode51.N皇后
 * n皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 *
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 看到这种形式的输出，就应该搞一个函数来整成这种形式呀
 */
public class SolveNQueens51 {
    /**
     * 直接的想法是枚举所有可能的组合，然后可以用DP来加速
     * 但是如何枚举呢？
     * 过阵子的想法：这不直接枚举回溯嘛
     * 外层循环是行循环，然后设置列集合，左上到右下的集合，右上到左下的集合
     * 更进一步的选择是用位运算
     */
    public List<List<String>> solveNQueens(int n) {
        //这里用集合更巧妙，集合可以直接判断是否存在
        Set<Integer> cols = new HashSet<>();
        Set<Integer> leftTopRights = new HashSet<>();
        Set<Integer> rightTopLefts = new HashSet<>();

        //因为结果是需要返回所有的解决方案，所以需要传入所有组合
        List<List<String>> solutions = new ArrayList<>();
        //还需要一个棋盘来保存每一种方案皇后的位置，因为行可以从0到n-1，所以可以直接用一个简单的数组来保存，queen[3]=2表示第4行第3列有皇后
        int[] queens = new int[n];
//        Arrays.fill(queens, -1);                //个人觉得这里fill-1可以不需要
        backtrack(solutions, queens, 0, n, cols, leftTopRights, rightTopLefts);
        return solutions;
    }

    //r代表下一个皇后在第几行，n代表一共可以多少行
    private void backtrack(List<List<String>> solutions, int[] queens, int row, int length, Set<Integer> cols, Set<Integer> leftToRights, Set<Integer> rightTopLefts) {
        //判断跳出条件是否满足
        if (row == length) {
            //这里需要用queen新生成一个列表放进来，不能用queen，会重复的
            solutions.add(generateBoard(queens, length));
            return;
        }
        //for循环枚举每一种情况，i代表列坐标
        for (int i=0; i < length; i++) {
            if (cols.contains(i))
                continue;
            //左上到右下，行坐标减去列坐标相等就在一条线上
            if (leftToRights.contains((row-i)))
                continue;
            //右上到左下，行坐标+列坐标
            if (rightTopLefts.contains((row+i)))
                continue;
            //确定皇后位置
            queens[row] = i;
            cols.add(i);
            leftToRights.add((row-i));
            rightTopLefts.add((row+i));

            //回溯back
            backtrack(solutions, queens, row+1, length, cols, leftToRights, rightTopLefts);

            //返回原状态，这一行的皇后-1表示还没确定，最后queens数组不会有-1
            queens[row] = -1;
            cols.remove(i);
            leftToRights.remove((row-i));
            rightTopLefts.remove((row+i));
        }
    }

    private List<String> generateBoard(int[] queen, int length) {
        List<String> board = new ArrayList<>();
        //这里的i是行
        for (int i=0; i < length; i++) {
            char[] row = new char[length];
            Arrays.fill(row, '.');
            row[queen[i]] = 'Q';
            board.add(new String(row));             //数组转String可以直接new String(数组)
        }
        return board;
    }

    public static void main(String[] args)
    {
        SolveNQueens51 solveNQueens51 = new SolveNQueens51();
        System.out.println(solveNQueens51.solveNQueens(4).toString());
    }
}
