package medium;

public class GenerateMatrix59 {
    public int[][] generateMatrix(int n) {
        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        int directionIndex = 0;
        int[][] matrix = new int[n][n];
        int[][] visitedMatrix = new int[n][n];

        int row = 0;
        int col = 0;

        for (int i=0; i < n*n; i++) {
            matrix[row][col] = i+1;
            visitedMatrix[row][col] = 1;

            //用一个临时变量来记录下一个位置
            int nextRow = row + directions[directionIndex][0];
            int nextCol = col + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n || visitedMatrix[nextRow][nextCol] == 1) {
                directionIndex = (directionIndex+1) % 4;
            }
            row += directions[directionIndex][0];
            col += directions[directionIndex][1];
        }
        return matrix;
    }
}
