package hard;

public class MaximalRectangle85 {
    //leetcode有同学把这道题和84联系起来，秒啊～
    //https://leetcode-cn.com/problems/maximal-rectangle/comments/399947
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int m = matrix.length;
        int n = matrix[0].length;

        int ans = 0;
        int[] heights = new int[n];
        /*
        for (int j = 0; j < n; j++) {                           //先列再行，这么写就是完全错误的，因为本来的写法就是先遍历列再遍历行，愚蠢
            for (int i = 0; i < m; i++) {
                if (matrix[i][j] == '1')
                    heights[j] += 1;
                else heights[j] = 0;
            }
            ans = Math.max(ans, LargestRectangleArea84.momoStack_largestRectangleArea_opt(heights));
        }
        */

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1')
                    heights[j] += 1;
                else heights[j] = 0;
            }
            ans = Math.max(ans, LargestRectangleArea84.momoStack_largestRectangleArea_opt(heights));
        }
        return ans;
    }
}
