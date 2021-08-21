package interviewer;

import java.util.*;
public class Main1_0808 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        int i = 0;
        while (i < T){
            int n = Integer.parseInt(in.next());
            int k = Integer.parseInt(in.next());

            int j = 0;
            Integer[] intArray = new Integer[n];
            while(j < n) {
                intArray[j] = Integer.valueOf(in.next());
                j++;
            }
            getK(intArray, k);
            i++;
        }
    }

    public static void getK(Integer[] intArray, int k) {
        if(k >= intArray.length || intArray.length == 0) {
            System.out.println("NO");
            return;
        }
        // System.out.println("NO");
        // 最直接的方法：排序，但是要注意严格
        Arrays.sort(intArray);
        if(k == 0) {
            System.out.println("YES");
            System.out.println(intArray[0]);
            return;
        }
        if (!intArray[k].equals(intArray[k - 1])) {
            System.out.println("YES");
            System.out.println(intArray[k-1]+1);
            return;
        }
        System.out.println("NO");
    }
    /**
     * 2
     * 6 6
     * 1 6 6 2 1 3
     * 6 3
     * 1 6 5 2 2 5
     */
}
