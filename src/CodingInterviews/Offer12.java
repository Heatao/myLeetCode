package CodingInterviews;

public class Offer12 {
    /**
     * 之前一遍做对了，第二次做完全没有记忆hhh
     * 朴素的反应是用回溯
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                backTrack(board, visited, word, i, j, 0);
            }
        }
        return tag;
    }

    private boolean tag = false;
    private void backTrack(char[][] board, boolean[][] visited, String word, int rowIndex, int colIndex, int index) {
        if (index >= word.length()) {
            tag = true;
            return;
        }
        if (tag || rowIndex < 0 || rowIndex >= board.length || colIndex < 0 || colIndex >= board[0].length || visited[rowIndex][colIndex])
            return;
        if (board[rowIndex][colIndex] != word.charAt(index))
            return;
        visited[rowIndex][colIndex] = true;
        backTrack(board, visited, word, rowIndex-1, colIndex, index+1);
        backTrack(board, visited, word, rowIndex, colIndex-1, index+1);
        backTrack(board, visited, word, rowIndex+1, colIndex, index+1);
        backTrack(board, visited, word, rowIndex, colIndex+1, index+1);
        visited[rowIndex][colIndex] = false;
    }

    public static void main(String[] args) {
        Offer12 offer12 = new Offer12();
        char[][] board = {{'a', 'b'}};
        String word = "ba";
        System.out.println(offer12.exist(board, word));
    }
}
