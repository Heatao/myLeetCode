package medium;

/**
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 */
public class CanJump55 {
    /**
     * 下面这段代码会超出时间限制
     * 首先是用递归，会有一个程序栈进出的消耗
     * 其次还有冗余，可能会多次到达一个曾经到达过的点
     */
    public boolean canJump(int[] nums) {
        int length = nums.length;
        return tryJump(nums, 0, length);
    }

    public boolean tryJump(int[] nums, int startIndex, int length) {
        if (startIndex == length-1)
            return true;
        int thisTry = nums[startIndex];
        for (int i = thisTry; i > 0; i--) {
            if (thisTry >= length - startIndex)
                return true;
            else {
                boolean thisFlag = tryJump(nums, startIndex + i, length);
                if (thisFlag)
                    return true;
            }
        }
        return false;
    }

    /*
    根本不必像上面这样用递归
    因为目前能到的最远的位置之前的全部也能到
     */
    public boolean others_canJump(int[] nums) {
        int length = nums.length;
        int nowMax = 0;
        for (int i=0; i < length; i++) {
            if (i <= nowMax) {          //代表i可抵达
                nowMax = Math.max(i + nums[i], nowMax);
                if (nowMax >= length-1)
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CanJump55 canJump55 = new CanJump55();
        int[] nums = {2,5,0,0};
        System.out.println(canJump55.canJump(nums));
    }
}
