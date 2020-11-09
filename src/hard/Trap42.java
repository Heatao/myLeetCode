package hard;

import java.util.Stack;

/**
 * LeetCode42.接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 提示：
 * n == height.length
 * 0 <= n <= 3 * 104
 * 0 <= height[i] <= 105
 */
public class Trap42 {
    /**
     * 第一反应是否可以用动态规划呢？但是状态转移参数是什么呢？
     * 朴素的想法：先找到最高点（可能有多个，找其中一个即可），然后向两边分别找两边第二高的点，计算中间的雨水量，然后向两边重复直到两边尽头
     * 做完之后看其实是分治法
     */
    public int mySolution_trap(int[] height) {
        int topIndex = 0;
        int topNum = 0;
        for (int i = 0; i < height.length; i++) {
            if(topNum < height[i]) {
                topIndex = i;
                topNum = height[i];
            }
        }
        int amount = 0;
        amount += trapLeft(height, topIndex);
        amount += trapRight(height, topIndex);
        return amount;
    }

    private int trapLeft(int[] height, int topIndex) {
        //到最后一个(第一个)柱子就停止
        if (topIndex <= 0) return 0;
        int secondHeightNum = -1;
        int secondHeightIndex = -1;
        for (int i = 0; i < topIndex; i++) {
            if (secondHeightNum < height[i]) {
                secondHeightNum = height[i];
                secondHeightIndex = i;
            }
        }

        int thisAmount = 0;
        for (int i = secondHeightIndex+1; i < topIndex; i++) {
            thisAmount += secondHeightNum - height[i];
        }
        thisAmount += trapLeft(height, secondHeightIndex);
        return thisAmount;
    }

    private int trapRight(int[] height, int topIndex) {
        if (topIndex >= height.length-1 || topIndex == -1) return 0;
        int secondHeightNum = -1;
        int secondHeightIndex = -1;
        for (int i = topIndex+1; i < height.length; i++) {
            if (secondHeightNum < height[i]) {
                secondHeightNum = height[i];
                secondHeightIndex = i;
            }
        }

        int thisAmount = 0;
        for (int i = topIndex+1; i < secondHeightIndex; i++) {
            thisAmount += secondHeightNum - height[i];
        }
        thisAmount += trapRight(height, secondHeightIndex);
        return thisAmount;
    }

    /**
     * 用单调栈的做法，很精妙
     * 只需要O(n)的时间复杂度
     * 一层层的接水，每次出栈保留上次的位置
     * https://leetcode-cn.com/problems/trapping-rain-water/solution/dan-diao-zhan-jie-jue-jie-yu-shui-wen-ti-by-sweeti/
     */
    public int trap(int[] height) {
        if (height == null) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            while(!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int curIdx = stack.pop();
                // 如果栈顶元素一直相等，那么全都pop出去，只留第一个。
                while (!stack.isEmpty() && height[stack.peek()] == height[curIdx]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    int stackTop = stack.peek();
                    // stackTop此时指向的是此次接住的雨水的左边界的位置。右边界是当前的柱体，即i。
                    // Math.min(height[stackTop], height[i]) 是左右柱子高度的min，减去height[curIdx]就是雨水的高度。
                    // i - stackTop - 1 是雨水的宽度。
                    ans += (Math.min(height[stackTop], height[i]) - height[curIdx]) * (i - stackTop - 1);
                }
            }
            stack.add(i);
        }
        return ans;
    }


    public static void main(String[] args) {
        Trap42 trap42 = new Trap42();
        int[] height = {0,2,0};
        System.out.println(trap42.mySolution_trap(height));
    }
}
