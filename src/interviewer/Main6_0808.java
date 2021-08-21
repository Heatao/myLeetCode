package interviewer;

import java.util.Scanner;

public class Main6_0808 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] cycle = new int[3];
        for (int i = 0; i < N; i++) {
            cycle[0] = in.nextInt();
            cycle[1] = in.nextInt();
            cycle[2] = in.nextInt();
            getAns(cycle);
        }
    }

    private static void getAns(int[] cycle){
        int ans = 0;
        int x = cycle[0];
        int y = cycle[1];
        int r = cycle[2];
        int absX = Math.abs(x);
        int absY = Math.abs(y);

        if (r > absX) ans += 2;
        else if (r == absX) ans += 1;
        if (r > absY) ans += 2;
        else if (r == absY) ans += 1;

        if (absX*absX + absY*absY == r*r) ans--;
        System.out.println(ans);
    }
}
