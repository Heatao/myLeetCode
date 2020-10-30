package hard;

/**
 * LeetCode37.解数独
 * 编写一个程序，通过填充空格来解决数独问题。
 * 一个数独的解法需遵循如下规则：
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 */
public class SolveSudoku37 {
    /**
     * 参考了题解的答案
     * 用最简单的思路，递归枚举每一种可能，先进行初始化确定哪些数字已被填充
     * 参考：https://leetcode-cn.com/problems/sudoku-solver/solution/hui-su-fa-jie-shu-du-by-i_use_python/
     */
    public void mySolution_solveSudoku(char[][] board) {
        boolean[][] colsUsed = new boolean[9][9];
        boolean[][] rowsUsed = new boolean[9][9];
        boolean[][][] boxesUsed = new boolean[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    continue;
                //下面这种写法是错误的，因为数字用ascii码存储的话并不是从0开始一一对应的，并且也不需要强制转换(int)
                //int thisNum = (int) board[i][j];
                //下面需要-1，因为board从0开始，num只为0-8
                int thisNum = board[i][j] - '1';
                rowsUsed[i][thisNum] = true;
                colsUsed[j][thisNum] = true;
                boxesUsed[i/3][j/3][thisNum] = true;
            }
        }
        recursiveSolveSudoku(board, rowsUsed, colsUsed, boxesUsed, 0, 0);
    }

    private boolean recursiveSolveSudoku(char[][] board, boolean[][] rowsUsed, boolean[][] colsUsed, boolean[][][] boxesUsed, int row, int col){
        //先从行开始，行到头则从列开始
        if (col == board[0].length){
            col = 0;
            row += 1;
            if (row == board.length)
                return true;
        }

        if (board[row][col] != '.')
            return recursiveSolveSudoku(board, rowsUsed, colsUsed, boxesUsed, row, col+1);
        //这里num是下标的num，对应的数字应该+1
        for (int num = 0; num < 9; num++){
            //or是有true则为true，这里需要所有皆为false才能满足
            boolean canUse = !(rowsUsed[row][num] || colsUsed[col][num] || boxesUsed[row/3][col/3][num]);
            if (canUse){
                rowsUsed[row][num] = true;
                colsUsed[col][num] = true;                          //这里粗心写成cow（因为是复制的），搞了我好久，裂开
                boxesUsed[row/3][col/3][num] = true;

                board[row][col] = (char)(num + '1');
                //如果成功则继续，如果是正确的情况下，会一直dfs下去直到最后一个被填满，返回true，如果没有继续dfs递归下去则会执行下面的重置代码并返回false
                if (recursiveSolveSudoku(board, rowsUsed, colsUsed, boxesUsed, row, col+1))
                    return true;
                //如果失败则把修改部分改回去，因为全部为false才能到这里，所以也全改回false，这里需要自己写，懂得吧，不要怕麻烦
                board[row][col] = '.';
                rowsUsed[row][num] = false;
                colsUsed[col][num] = false;
                boxesUsed[row/3][col/3][num] = false;
            }
        }
        return false;
    }
}
