package hard;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * ⚠️这里是返回的数量
 * 输入：n = 4
 * 输出：2
 */
public class TotalNQueens52 {
    public int totalNQueens(int n) {
        Set<Integer> cols = new HashSet<>();
        Set<Integer> leftTopRight = new HashSet<>();
        Set<Integer> rightTopLeft = new HashSet<>();
        int ansNum = 0;
        //回溯
        ansNum = backtrack(0, n, cols, leftTopRight, rightTopLeft);
        return ansNum;
    }

    private int backtrack(int row, int length, Set<Integer> cols, Set<Integer> leftTopRights, Set<Integer> rightTopLefts) {
        if (row == length) {
            return 1;                   //这里return 1表示当下这种情况成立，一开始写不出来这里可以先写后面的
        }
        int count = 0;
        for (int i=0; i < length; i++) {
            if (cols.contains(i))
                continue;
            //左上到右下，行坐标减去列坐标相等就在一条线上
            if (leftTopRights.contains((row-i)))
                continue;
            //右上到左下，行坐标+列坐标
            if (rightTopLefts.contains((row+i)))
                continue;
            //确定皇后位置
            cols.add(i);
            leftTopRights.add((row-i));
            rightTopLefts.add((row+i));

            //回溯back
            count += backtrack(row+1, length, cols, leftTopRights, rightTopLefts);

            //返回原状态，这一行的皇后-1表示还没确定，最后queens数组不会有-1
            cols.remove(i);
            leftTopRights.remove((row-i));
            rightTopLefts.remove((row+i));
        }
        return count;
    }
}
