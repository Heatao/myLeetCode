package test;

import java.util.ArrayList;
import java.util.List;

/**
 * 适用于一个顺时针旋转遍历，模拟机器人的题
 */
public class SpiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int total = rows * columns;

        // visited数组判断是否走过
        boolean[][] visited = new boolean[rows][columns];

        int row = 0, column = 0;
        // 模拟方向
        // 这里用二维数组表示方向，可以看ArrayForDirection中的描述
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;

        for (int i = 0; i < total; i++) {
            order.add(matrix[row][column]);
            visited[row][column] = true;

            // nextRow和nextColumn是为了验证一下是否需要转向和是否越界，越界就需要转向
            int nextRow = row + directions[directionIndex][0];
            int nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }

            // 这里不能写row = nextRow，因为directionIndex可能转向改变了
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }
}
