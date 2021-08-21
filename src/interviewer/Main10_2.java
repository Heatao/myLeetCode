package interviewer;

import java.util.Scanner;

public class Main10_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int Q = in.nextInt();
        in.nextLine();
        String nLine = in.nextLine().trim();
        if (nLine.trim().split("\\s").length > n)
            throw new RuntimeException("asdasd");

        int[] pArray = new int[Q];
        for (int j = 0; j < Q; j++) {
            pArray[j] = in.nextInt();
        }

        for (int j = 0; j < Q; j++) {
            nLine = move(nLine, pArray[j]);
        }
        // System.out.println(nLine);
        String[] aList = nLine.split("\\s");
        for (String each : aList) {
            System.out.print(Integer.parseInt(each));
            System.out.print(Integer.parseInt(" "));
        }
    }

    private static String move(String nLine, int leftPart) {
        String sub1 = nLine.substring(0, leftPart*2-1).trim();                   // 左闭右开
        String sub2 = nLine.substring(leftPart*2).trim();
        return sub2 + ' ' +
                sub1;
    }
}
