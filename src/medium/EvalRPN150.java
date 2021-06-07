package medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvalRPN150 {
    // 可能为负数
    public int evalRPN(String[] tokens) {
        if (tokens == null)
            throw new RuntimeException("input error.");
        Deque<Integer> stack = new ArrayDeque<>();
        for (String c : tokens) {
            if (c.length() >= 2 || (c.charAt(0) - '0' >= 0 && c.charAt(0) - '0' <= 9)) {
                stack.push(Integer.parseInt(c));
                continue;
            }

            int right = stack.pop();
            int left = stack.pop();
            switch (c) {
                case "+":
                    stack.push(left + right);
                    break;
                case "-":
                    stack.push(left - right);
                    break;
                case "*":
                    stack.push(left * right);
                    break;
                case "/":
                    stack.push(left / right);
                    break;
                default:
                    throw new RuntimeException("input error.");
            }
        }
        return stack.poll();
    }

    // 下面是错的，因为一些中间值会算两次
    private int wrong(String[] tokens) {
        if (tokens == null)
            throw new RuntimeException("input error.");
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (String c : tokens) {
            int mayNum = c.charAt(0) - '0';
            if (mayNum >= 0 && mayNum <= 9) {
                stack.push(mayNum);
                continue;
            }
            int left = stack.pop();
            int right = stack.pop();
            int tmp;
            switch (c) {
                case "+":
                    tmp = left + right;
                    ans += tmp;
                    stack.push(tmp);
                    break;
                case "-":
                    tmp = left - right;
                    ans += tmp;
                    stack.push(tmp);
                    break;
                case "*":
                    tmp = left * right;
                    ans += tmp;
                    stack.push(tmp);
                    break;
                case "/":
                    tmp = left / right;
                    ans += tmp;
                    stack.push(tmp);
                    break;
                default:
                    throw new RuntimeException("input error.");
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        EvalRPN150 evalRPN150 = new EvalRPN150();
        System.out.println(evalRPN150.evalRPN(tokens));
    }
}
