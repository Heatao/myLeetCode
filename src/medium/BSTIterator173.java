package medium;

import test.TreeNode;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 直接搞个list来存的方法就不说了，还是用非递归的中序遍历来做吧
 */
public class BSTIterator173 {
    private TreeNode curNode;
    private Deque<TreeNode> stack;

    public BSTIterator173(TreeNode root) {
        curNode = root;
        stack = new LinkedList<>();
    }

    // 下面就是非递归的中序遍历啦
    public int next() {
        while (curNode != null) {
            stack.push(curNode);                   // 这里是push curNode而不是curNode.left喔
            curNode = curNode.left;
        }
        curNode = stack.pop();
        int val = curNode.val;
        curNode = curNode.right;                        // 这里不庸官right喔
        return val;
    }

    public boolean hasNext() {
        return curNode != null || !stack.isEmpty();
    }
}
