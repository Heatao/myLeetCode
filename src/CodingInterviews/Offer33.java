package CodingInterviews;

import java.util.Stack;

public class Offer33 {
    // 记住二叉搜索树的根节点在最后哟
    public boolean verifyPostorder(int[] postorder) {
        return judgeOrderTree(postorder, 0, postorder.length-1);
    }

    private boolean judgeOrderTree(int[] postorder, int start, int end) {
        if (start >= end) return true;
        int root = postorder[end];
        int i = start;
        while (postorder[i] < root) i++;
        int j = i;
        while (postorder[j] > root) j++;
        // 下面右子树传的参数必须是END-1喔，因为是右子树呀，和二分查找不一样
        return j==end && judgeOrderTree(postorder, start, i-1) && judgeOrderTree(postorder, i, end-1);
    }

    /**
     * 应该算是规律，参考sdwwld大佬的解释
     * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solution/di-gui-he-zhan-liang-chong-fang-shi-jie-jue-zui-ha/
     */
    public boolean others_verifyPostorder(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int parent = Integer.MAX_VALUE;
        //注意for循环是倒叙遍历的
        for (int i = postorder.length - 1; i >= 0; i--) {
            int cur = postorder[i];
            //当如果前节点小于栈顶元素，说明栈顶元素和当前值构成了倒叙，
            //说明当前节点是前面某个节点的左子节点，我们要找到他的父节点
            while (!stack.isEmpty() && stack.peek() > cur)
                parent = stack.pop();
            //只要遇到了某一个左子节点，才会执行上面的代码，才会更
            //新parent的值，否则parent就是一个非常大的值，也就
            //是说如果一直没有遇到左子节点，那么右子节点可以非常大
            if (cur > parent)
                return false;
            //入栈
            stack.add(cur);
        }
        return true;
    }

}
