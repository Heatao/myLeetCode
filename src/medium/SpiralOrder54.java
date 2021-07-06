package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 */
public class SpiralOrder54 {
    /**
     * 模拟
     * 直接的思路是用一个相同的矩阵保存是否被访问过
     * 然后先横再纵酱紫
     *
     * 下面完全错误了，不仅是边界溢出问题，而且四个方向都没确定
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int length = matrix[0].length;
        int width = matrix.length;
        List<Integer> ans = new ArrayList<>();

        int[][] visited = new int[length][width];           //java会将值初始化为0
        int nowL = 0;
        int nowW = 0;

        int direction = 1;              //1表示横，2表示竖
        boolean lastIsTurn = false;                 //当一次转向后变为true
        while (true) {
            if (direction == 1) {
                //表示遍历到这一条直线的终点
                if (visited[nowL][nowW+1] == -1) {
                    if (lastIsTurn)
                        break;
                    else {
                        direction = 2;
                        lastIsTurn = true;
                    }
                } else {
                    nowW += 1;
                    lastIsTurn = false;
                    ans.add(matrix[nowL][nowW]);
                }

            }
            else {
                if (visited[nowL+1][nowW] == -1) {
                    if (lastIsTurn)
                        break;
                    else {
                        direction = 1;
                        lastIsTurn = true;
                    }
                } else {
                    nowL += 1;
                    lastIsTurn = false;
                    ans.add(matrix[nowL][nowW]);
                }
            }
        }
        return ans;
    }

    public List<Integer> official_spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;

        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};            //这里用二维数组表示方向，可以看ArrayForDirection中的描述
        int directionIndex = 0;

        for (int i = 0; i < total; i++) {
            order.add(matrix[row][column]);
            visited[row][column] = true;

            //nextRow和nextColumn存粹就是为了验证一下是否需要转向和是否越界
            int nextRow = row + directions[directionIndex][0];
            int nextColumn = column + directions[directionIndex][1];

            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }

    private List<Integer> do2nd(int[][] matrix) {
        // col表示左右，row表示上下，所以directions一开始是{0,1}，第二个是{1,0}，因为col+1表示往下走
        List<Integer> spiOrders = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return spiOrders;

        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};                                            // 易错点1
        int directionIndex = 0;
        int m = matrix.length, n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int row = 0, col = 0;
        int total = m*n;
        for(int i = 0; i < total; i++) {                                                                // 易错点2
            spiOrders.add(matrix[row][col]);
            visited[row][col] = true;

            int nextRow = directions[directionIndex][0] + row;                                          // 易错点3
            int nextCol = directions[directionIndex][1] + col;
            if(nextRow >= m || nextRow < 0 || nextCol >= n || nextCol < 0 || visited[nextRow][nextCol]) // 易错点4
                directionIndex  = (directionIndex + 1) % 4;
            row = directions[directionIndex][0] + row;
            col = directions[directionIndex][1] + col;
        }
        return spiOrders;
    }
}
