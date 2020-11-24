package medium;

/**
 * leetCode48.旋转图像
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 * 必须原地旋转
 */
public class Rotate48 {
    /**
     * 规律：
     * 横->纵：从上往下数第几行->从右往左数第几列
     * 纵->横：从左往右数第几列->从上往下数第几行
     * 如何原地？正方形的边缘，取一行的前n-1个；取内正方形
     */
    public void rotate(int[][] matrix) {
        int m = matrix.length;
        for (int i = 0; i < m; i++) {
            if (i >= m/2)
                break;

            for (int col = i; col < m-1-i; col++) {
                recursionChange(matrix, i, col, matrix[i][col], i, col);
            }
        }
    }

    private void recursionChange(int[][] matrix, int row, int col, int value, int stopRow, int stopCol) {
        int nextRow = col;
        int nextCol = matrix.length-1-row;
        int tmp = matrix[nextRow][nextCol];
        matrix[nextRow][nextCol] = value;
        if (nextRow == stopRow && nextCol == stopCol)           //更简单的直接计数就可以了
            return;
        recursionChange(matrix, nextRow, nextCol, tmp, stopRow, stopCol);
    }

    public static void main(String[] args) {
        Rotate48 rotate48 = new Rotate48();
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        rotate48.rotate(matrix);
    }
}
