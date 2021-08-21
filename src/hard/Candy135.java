package hard;

import java.util.Arrays;

/**
 * 这个题还有一个复杂版本，就是孩子围成一个圈的情况，
 * 目前的一个思路，用一个队列，然后从左往右，然后从右往左，判断初始节点是否符合条件，如果不符合，则递归这个过程
 */
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

    /**
     * 这个思路更容易理解，最后一步Math.max(left[i], right[i])能保证当前这个节点满足最优，所有的局部最优可以达到最终最优
     */
    public int others_candy(int[] ratings) {
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for(int i = 1; i < ratings.length; i++)
            if(ratings[i] > ratings[i - 1])
                left[i] = left[i - 1] + 1;
        int count = left[ratings.length - 1];
        for(int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1])
                right[i] = right[i + 1] + 1;
            count += Math.max(left[i], right[i]);
        }
        return count;
    }

}
