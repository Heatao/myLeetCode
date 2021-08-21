package interviewer;

import java.util.HashMap;
import java.util.Scanner;

public class Main4_0808 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = Integer.parseInt(in.nextLine());
        String[] line = in.nextLine().split("\\s");
        int[] array1 = new int[len/2];
        int[] array2 = new int[len/2];
        for (int i = 0; i < len / 2; i++) {
            array1[i] = Integer.parseInt(line[i]);
        }
        for (int i = len / 2; i < len; i++) {
            array2[i - len / 2] = Integer.parseInt(line[i]);
        }

        // 用一个map去存已经改变的数
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int cmt = 0;
        for (int i = 0; i < len / 2; i++) {
            int num1 = array1[i];
            int num2 = array2[i];
            if (num1 == num2) continue;
            if (!hashMap.containsKey(array1[i])) {
                hashMap.put(num1, num2);
                cmt++;
            }
            else {
                if (hashMap.get(num1) == num2) continue;
                // num1已经变了，所以需要变num2
                if (!hashMap.containsKey(num2))
                    hashMap.put(num2, num1);
                cmt++;
            }
        }
        System.out.println(cmt);
    }
    /**
     * 10
     * 4 2 1 5 2 10 2 1 5 8
     */
}
