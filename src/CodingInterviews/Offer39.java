package CodingInterviews;

import java.util.ArrayDeque;
import java.util.Deque;

public class Offer39 {
    //下面完全可以不用stack，可以用一个res保留数字+一个count计数
    public int majorityElement(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (stack.isEmpty())
                stack.push(nums[i]);
            else if (stack.peek() != nums[i])
                stack.pop();
            else stack.push(nums[i]);
        }
        return stack.pop();
    }
}
