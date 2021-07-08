package medium;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 提示：
 m == grid.length
 n == grid[i].length
 1 <= m, n <= 300
 grid[i][j] 的值为 '0' 或 '1'

 */
public class NumIslands200 {
    /**
     * 朴素的想法，写一个visited数组
     */
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    count += 1;
                    findIsland(grid, visited, i, j);
                }
            }
        }
        return count;
    }

    private void findIsland(char[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length)
            return;
        if (grid[i][j] == '1' && !visited[i][j]) {
            visited[i][j] = true;
            findIsland(grid, visited, i+1, j);
            findIsland(grid, visited, i-1, j);
            findIsland(grid, visited, i, j+1);
            findIsland(grid, visited, i, j-1);
        }
    }

    public int do2nd(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '0' || visited[i][j]) continue;
                count++;
                // 调用尽可能遍历函数
                findMore(grid, visited, i, j);
            }
        }
        return count;
    }

    // 这里应该写递归函数
    private void findMore(char[][] grid, boolean[][] visited, int row, int col) {
        if(row >= visited.length || row < 0 || col >= visited[0].length || col < 0 || visited[row][col])
            return;
        if(grid[row][col] == '1') {                             // 易错点1:这里忽略判断是否为1
            visited[row][col] = true;
            findMore(grid, visited, row-1, col);           // 易错点2:这里不是往下和往右就可以的
            findMore(grid, visited, row, col-1);
            findMore(grid, visited, row+1, col);
            findMore(grid, visited, row, col+1);
        }
    }

    public static void main(String[] args) {
        char[][] grid = {{'1','1','1'},{'0','1','0'},{'1','1','1'}};
        NumIslands200 numIslands200 = new NumIslands200();
        System.out.println(numIslands200.do2nd(grid));
    }
}
