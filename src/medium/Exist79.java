package medium;

public class Exist79 {
    /*
    咋一看很像机器人转弯那道题，定义方向数组，但是这道题转弯需要判断，所以可以不用机器人转弯那样去做
    因为转弯可能有多条路可以走，所以可能需要回溯
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] used = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    used[i][j] = true;                  //这一步容易被忽略
                    if(checkBoard(board, word, used, i, j, 1))
                        return true;
                    used[i][j] = false;
                }
            }
        }
        return false;
    }

    //sr:start row; sc:start col,
    private boolean checkBoard(char[][] board, String word, boolean[][] used, int srow, int scol, int index) {
        if (index == word.length())
            return true;
        if (srow > 0 && board[srow-1][scol] == word.charAt(index) && !used[srow - 1][scol]) {
            used[srow-1][scol] = true;
            if (checkBoard(board, word,used, srow-1, scol, index+1))
                return true;
            used[srow-1][scol] = false;
        }
        if (scol > 0 && board[srow][scol-1] == word.charAt(index) && !used[srow][scol - 1]) {
            used[srow][scol-1] = true;
            if (checkBoard(board, word,used, srow, scol-1, index+1))
                return true;
            used[srow][scol-1] = false;
        }
        if (srow + 1 < board.length && board[srow+1][scol] == word.charAt(index) && !used[srow+1][scol]) {
            used[srow+1][scol] = true;
            if (checkBoard(board, word,used, srow+1, scol, index+1))
                return true;
            used[srow+1][scol] = false;
        }
        if (scol + 1 < board[0].length && board[srow][scol+1] == word.charAt(index) && !used[srow][scol+1]) {
            used[srow][scol+1] = true;//这个放的位置需要确定一下
            if (checkBoard(board, word,used, srow, scol+1, index+1))
                return true;
            used[srow][scol+1] = false;
        }
        return false;
    }

    public static void main(String[] args) {
//        char[][] board = {{'A','B','C','E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
//        String word = "ABCB";
        char[][] board = {{'a', 'a'}};
        String word = "aaa";
        Exist79 exist79 = new Exist79();
        System.out.println(exist79.exist(board, word));
    }
}
