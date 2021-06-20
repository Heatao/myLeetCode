package CodingInterviews;

public class Offer29 {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return new int[]{};
        int m = matrix.length;
        int n = matrix[0].length;
        int total = m*n;

        boolean[][] visited = new boolean[m][n];
        int[] orders = new int[total];

        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        int direction = 0;
        int row = 0, col = 0;
        for (int i = 0; i < total; i++) {
            visited[row][col] = true;
            orders[i] = matrix[row][col];

            int newRow = row + directions[direction][0];
            int newCol = col + directions[direction][1];
            if (newCol < 0 || newRow < 0 || newRow >= m || newCol >= n || visited[newRow][newCol])
                direction = (direction + 1) % 4;

            row += directions[direction][0];
            col += directions[direction][1];
        }
        return orders;
    }

}
