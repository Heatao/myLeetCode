package CodingInterviews;

public class Offer04 {
    /**
     * 猜测是用二分查找
     * 下面的代码是逻辑错了，下面的适用于下一行一定大于上一行的情况
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0, high = m-1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            // 这里要写 < 喔！
            if (matrix[mid][0] < target)
                low = mid;
            else if (matrix[mid][0] > target)
                high = mid - 1;
            else {
                low = mid;
                break;
            }
        }
        int row = low;

        low = 0;
        high = n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (matrix[row][mid] < target)
                low = mid + 1;
            else high = mid;
        }
        return matrix[row][low] == target;
    }

    /**
     * 结果是模仿二叉树的做法呢！很精妙
     * 把图画出来做
     */
    public boolean others_findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0, j = n-1;
        while (i >= 0 && j < n && i < m && j >= 0) {
            if (matrix[i][j] > target) j--;
            else if (matrix[i][j] < target) i++;
            else return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,4,7,11,15}, {2,5,8,12,19}, {3,6,9,16,22}, {10,13,14,17,24}, {18,21,23,26,30}};
        int target = 10;
        Offer04 offer04 = new Offer04();
        System.out.println(offer04.findNumberIn2DArray(matrix, target));
    }
}
