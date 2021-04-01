package medium;

/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 */
public class SearchMatrix74 {
    /*
     * 第一反应就是用二分查找，先第一列做二分，找到对应的行再二分
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int row = rowFind(matrix, 0, m-1, target);
        if (row == -1)
            return false;
        int col = colFind(matrix, row, 0, n-1, target);
        if (col == -1)
            return false;
        return true;
    }

    private int rowFind(int[][] matrix, int s, int e, int target) {
//        if (matrix[e][0] < target || matrix[s][0] > target)
//            return -1;
        if (s < 0 || e >= matrix.length)
            return -1;
        if (matrix[s][0] == target)
            return s;
        if (matrix[e][0] == target)
            return e;
        if (e - s <= 1)
            if (matrix[e][0] > target)
                return s;
            else return e;
        int mid = (s + e) / 2;
        if (matrix[mid][0] <= target)
            return rowFind(matrix, mid, e, target);
        else return rowFind(matrix, s, mid-1, target);
    }

    private int colFind(int[][] matrix, int row, int s, int e, int target) {
        if (matrix[row][s] == target)
            return target;
        if (s == e)
            return -1;
        if (matrix[row][s] > target || matrix[row][e] < target)
            return -1;
        int mid = (s + e) / 2;
        if (matrix[row][mid] < target)
            return colFind(matrix, row, mid+1, e, target);
        else return colFind(matrix, row, s, mid, target);
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7}, {10,11,16,20}, {23,30,34,60}};
//        int[][] matrix = {{1}, {3}, {5}};
        SearchMatrix74 searchMatrix74 = new SearchMatrix74();
        System.out.println(searchMatrix74.searchMatrix(matrix, 30));
    }
}
