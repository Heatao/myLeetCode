package medium;

/**
 * LeetCode11.盛水最多的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 示例：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 */
public class maxArea {
    /**
     * 最简单的做法：枚举所有组合，我觉得必修枚举所有，但要注意的是判断两条线是否高度
     * @param height
     * @return
     */
    public int mySolution_maxArea(int[] height) {
        int maxValue = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j=i+1; j < height.length; j++){
                int thisHeight = Math.min(height[i], height[j]);
                maxValue = Math.max(maxValue, thisHeight*(j-i));
            }
        }
        return maxValue;
    }

    /**
     * 核心思想：缩减搜索空间，相同类型题目167，240
     * 优化思想：有冗余，有一些组合其实是不用判断的，采用双指针的做法
     * 双指针的要点：指针每一次移动都意味着排除掉了一个柱子（排除一个柱子就减少了很多组合的可能）
     * 我们可以排除一个柱子原因在于，高度其实是由矮的柱子决定的，
     * @param height
     * @return
     */
    public int official_maxArea(int[] height) {
        int i = 0;
        int j = height.length-1;
        int maxValue = 0;
        while (i < j){
            maxValue = Math.max(maxValue, (j-i)*Math.min(height[i], height[j]));
            if (height[i] < height[j]){
                i++;
            }
            else {
                j--;
            }
        }
        return maxValue;
    }
}
