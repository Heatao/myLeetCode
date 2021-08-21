package interviewer;

import java.util.Arrays;
import java.util.Scanner;

public class Main10_0811 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int Q = in.nextInt();
        int[] aArray = new int[n];
        for (int i = 0; i < n; i++) {
            aArray[i] = in.nextInt();
        }
//        in.nextLine();
//        String nLine = in.nextLine().trim();

        int[] pArray = new int[Q];
        for (int j = 0; j < Q; j++) {
            pArray[j] = in.nextInt();
        }

        for (int j = 0; j < Q; j++) {
//            nLine = move(nLine, pArray[j]);
            move2(aArray, pArray[j]);
        }
//        System.out.println(nLine);
//        for (String each : nLine.trim().split("\\s")){
//            System.out.print(Integer.parseInt(each));
//            System.out.print(" ");
//        }
        for (int i = 0; i < n; i++) {
            System.out.print(aArray[i]);
            System.out.print(" ");
        }
    }

    private static String move(String nLine, int leftPart) {
        String sub1 = nLine.substring(0, leftPart*2-1).trim();                   // 左闭右开
        String sub2 = nLine.substring(leftPart*2).trim();
        StringBuilder sb = new StringBuilder(sub2);
        sb.append(' ');
        sb.append(sub1);
        return sb.toString();
    }

    private static void move2(int[] aArray, int leftIndex) {
        int[] tmpArray = new int[leftIndex];
        int nextI = 0;
        for (int i = 0; i < aArray.length; i++) {
            if (i < leftIndex) {
                tmpArray[i] = aArray[i];
            }
            else {
                aArray[i-leftIndex] = aArray[i];
                nextI = i-leftIndex+1;
            }
        }
        for (int i = 0; i < leftIndex; i++) {
            aArray[nextI] = tmpArray[i];
            nextI++;
        }
    }
}
