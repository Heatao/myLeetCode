package interviewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main9_0808 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        int[] hards = new int[N];
        for (int i = 0; i < N; i++) {
            hards[i] = in.nextInt();
        }
        backtrack(hards, M, new ArrayList<>(), new boolean[N]);
        System.out.println(cmt);
    }

    static int cmt = 0;
    private static void backtrack(int[] hards, int M, List<Integer> tmpList, boolean[] visited) {
        if (tmpList.size() == hards.length) {
            boolean tag = true;
            for (int i = 0; i < tmpList.size() - 1; i++) {
                if (tmpList.get(i) > tmpList.get(i+1) + M) {
                    tag = false;
                    break;
                }
            }
            if (tag) {
                cmt++;
            }
        }

        for (int i = 0; i < hards.length; i++) {
            if (!visited[i]) {
                tmpList.add(hards[i]);
                visited[i] = true;
                backtrack(hards, M, tmpList, visited);
                tmpList.remove(tmpList.size()-1);
                visited[i] = false;
            }
        }
    }
}
