package CodingInterviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 输出连续正整数序列，暗示双指针滑动窗口
 */
public class Offer57_2 {
    /**
     * 这道题应该用List<int[]>来存的，这样少一步转换
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        if (target <= 2) throw new RuntimeException("Incorrect input data.");
        List<List<Integer>> allAns = new ArrayList<>();
        List<Integer> inAns = new ArrayList<>();
        int tmpSum = 0;
        for (int i = 1; i < target; i++) {
            inAns.add(i);
            tmpSum += i;
            if (tmpSum == target) {
                allAns.add(new ArrayList<>(inAns));
            }
            else {
                while (tmpSum > target) {
                    tmpSum -= inAns.get(0);
                    inAns.remove(0);
                }
                if (tmpSum == target) allAns.add(new ArrayList<>(inAns));
            }
        }

        return list2Array(allAns);
    }

    private int[][] list2Array(List<List<Integer>> allAns) {
        int m = allAns.size();
        // 原来二维数组不一定需要长度一致呀
        int[][] ans = new int[m][];
        for (int i = 0; i < m; i++) {
            ans[i] = allAns.get(i).stream().mapToInt(Integer::intValue).toArray();
        }
        return ans;
    }

    public static void main(String[] args) {
        int target1 = 15;
        Offer57_2 offer57_2 = new Offer57_2();
        int[][] ans = offer57_2.findContinuousSequence(target1);
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[i].length; j++) {
                System.out.println(ans[i][j]);
            }
        }
    }
}
