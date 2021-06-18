package CodingInterviews;

public class Offer13 {
    // 先用DP判断哪些格子能到达，然后再遍历一遍去数吧
    // 教训：DP的边界非常非常非常重要
    public int movingCount(int m, int n, int k) {
        boolean[][] access = new boolean[m][n];
        access[0][0] = true;
        // 需要判断边界
        for (int j = 1; j < n; j++) {
            if (access[0][j-1]) access[0][j] = judgeAc(0, j, k);
        }

        for (int i = 1; i < m; i++) {
            if (access[i-1][0]) access[i][0] = judgeAc(i, 0, k);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (access[i-1][j] || access[i][j-1])
                    access[i][j] = judgeAc(i, j, k);
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (access[i][j]) count++;
            }
        }
        return count;
    }

    private boolean judgeAc(int row, int col, int k) {
        int count = 0;
        while (row / 10 != 0) {
            count += row % 10;
            row /= 10;
        }
        count += row % 10;
        while (col / 10 != 0) {
            count += col % 10;
            col /= 10;
        }
        count += col % 10;
        if (count > k)
            return false;
        else return true;
    }

    public static void main(String[] args) {
        Offer13 offer13 = new Offer13();
        int m = 3;
        int n = 2;
        int k = 17;
        System.out.println(offer13.movingCount(m, n, k));
    }
}
