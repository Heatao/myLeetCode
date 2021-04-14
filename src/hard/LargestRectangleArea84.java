package hard;

import java.util.*;

/**
 * 根据leetcode评论区bill583同学的回复：
 * 这题考的基础模型其实就是：在一维数组中对每一个数找到第一个比自己小的元素。这类“在一维数组中找第一个满足某种条件的数”的场景就是典型的单调栈应用场景。
 */
public class LargestRectangleArea84 {
    /**
     * 暴力破解，话说这道题的暴力破解都不能直接想到呢
     * 遇到难题，第一时间反应不出来是什么算法（比如DP，二分之类的），就先写个暴力的解法，然后去优化冗余！
     */
    public int largestRectangleArea(int[] heights) {
        int area = 0;
        int lp = 0;
        int rp = 0;
        for (int i = 0; i < heights.length; i++) {
            lp = i;
            rp = i;
            while (lp >= 0) {
                if (heights[lp] < heights[i]) {
                    break;
                }
                lp--;                   //这里如果直接写的话会有问题，因为lp可以到-1，所以出去需要lp++
            }
            lp++;
            while (rp < heights.length) {
                if (heights[rp] < heights[i]) {
                    break;
                }
                rp++;
            }
            rp--;
            area = Math.max(heights[i]*(rp-lp+1), area);
        }
        return area;
    }

    public int momoStack_largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> momoStack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!momoStack.isEmpty() && heights[momoStack.peek()] >= heights[i]){
                momoStack.pop();
            }
            left[i] = momoStack.isEmpty() ? -1 : momoStack.peek();
            momoStack.push(i);
        }

        momoStack.clear();
        for (int i = n-1; i >= 0; i--) {
            while (!momoStack.isEmpty() && heights[momoStack.peek()] >= heights[i]) {
                momoStack.pop();
            }
            right[i] = momoStack.isEmpty() ? n : momoStack.peek();
            momoStack.push(i);
        }

        int area = 0;
        for (int i = 0; i < n; i++) {
            area = Math.max(area, heights[i] * (right[i] - left[i] - 1));   //这里-1是因为左右两边滴会多一位出来，根据例子理解
        }
        return area;
    }

    /**
     * 看题解，确实是有这么一回事
     */
    public static int momoStack_largestRectangleArea_opt(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);                                  //这一步很关键，如果不这样，会有元素没有右边的边界

        Deque<Integer> momoStack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!momoStack.isEmpty() && heights[momoStack.peek()] >= heights[i]){
                right[momoStack.pop()] = i;                                            //将此时栈中的元素出栈，证明height[i]小于栈元素的height
            }
            left[i] = momoStack.isEmpty() ? -1 : momoStack.peek();
            momoStack.push(i);
        }

        int area = 0;
        for (int i = 0; i < n; i++) {
            area = Math.max(area, heights[i] * (right[i] - left[i] - 1));   //这里-1是因为左右两边滴会多一位出来，根据例子理解
        }
        return area;
    }

    public static void main(String[] args) {
//        int[] nums = {2,1,5,6,2,3};
        int[] nums = {2,4};
        LargestRectangleArea84 largestRectangleArea84 = new LargestRectangleArea84();
        System.out.println(largestRectangleArea84.momoStack_largestRectangleArea(nums));
    }
}
