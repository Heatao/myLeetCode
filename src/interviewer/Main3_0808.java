package interviewer;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main3_0808 {
    public static void main(String[] args) {
        // 也许可以用单调栈
        Scanner in = new Scanner(System.in);
        int len = Integer.parseInt(in.nextLine());
        String[] line = in.nextLine().split("\\s");
        int[] arrayList = new int[len];
        for (int i = 0; i < len; i++) {
            arrayList[i] = Integer.parseInt(line[i]);
        }

        int[] prevList = new int[len];
        prevList[0] = 0;
//        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
//        priorityQueue.add(arrayList[0]);
        for (int i = 1; i < len; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arrayList[j] < arrayList[i]) {
                    max = Math.max(max, arrayList[j]);
                }
            }
            prevList[i] = max;
        }

        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += (i+1)*prevList[i];
        }
        System.out.println(sum);
    }
}
