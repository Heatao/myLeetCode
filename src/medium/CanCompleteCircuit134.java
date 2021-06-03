package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 这道题充分说明了，应该想清楚每个变量的含义再写，不能模棱两可
 */
public class CanCompleteCircuit134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        if (len <= 0 || len != cost.length)
            return 0;
        if (len == 1)
            return gas[0] >= cost[0] ? 0 : -1;

        List<Integer> starts = new ArrayList<>(len);
        // 从这里出发最少需要多少
        int[] minStartHere = new int[len];
        for (int i = 0; i < len; i++) {
            if (cost[i] < gas[i]) {
                starts.add(i);
                minStartHere[i] = 0;
            }
            else minStartHere[i] = cost[i] - gas[i];
        }

        int nowGas;
        for (int i : starts) {
            // nowGas表示经过i之后还剩多少油，这样的话会出现长度为1时候的特殊情况，因为我们画这个圆圈是向前画的，判断边界是len-1
            nowGas = gas[i] - cost[i];
            for (int j = i+1; j <= i + len; j++) {
                int step = j%len;
                if (nowGas < minStartHere[step]) {
                    minStartHere[i] = minStartHere[step] - nowGas;
                    break;
                }
                if (j-i == len-1)
                    return i;
                nowGas += gas[step] - cost[step];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        CanCompleteCircuit134 canCompleteCircuit134 = new CanCompleteCircuit134();
//        int[] gas = {65,1,2,3,4};
//        int[] cost = {4,4,1,5,1};
//        int[] gas = {2,3,4};
//        int[] cost = {3,4,3};
//        int[] gas = {1,2,3,4,5};
//        int[] cost = {3,4,5,1,2};
        int[] gas = {5};
        int[] cost = {4};
        System.out.println(canCompleteCircuit134.canCompleteCircuit(gas, cost));
    }
}
