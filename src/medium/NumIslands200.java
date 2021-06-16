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
}