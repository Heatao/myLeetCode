package hard;

import java.util.Arrays;

public class Candy135 {
    public int candy(int[] ratings) {
        int len = ratings.length;
        if (len <= 0)
            return 0;
        int[] eachCandy = new int[len];
        Arrays.fill(eachCandy, 1);
        if (len > 1 && ratings[0] > ratings[1]) {
            eachCandy[0] = 2;
        }
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i-1] && eachCandy[i] <= eachCandy[i-1])
                eachCandy[i] = eachCandy[i-1] + 1;
        }

        // 这个题的题意不是大于左右两边才多给喔！
        int sum = eachCandy[len-1];
        for (int i = len-2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1] && eachCandy[i] <= eachCandy[i+1])
                eachCandy[i] = eachCandy[i+1] + 1;
            sum += eachCandy[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        Candy135 candy135 = new Candy135();
        int [] ratings = {1, 2, 2};
        System.out.println(candy135.candy(ratings));
    }
}
