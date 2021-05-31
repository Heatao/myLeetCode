package medium;

import test.TreeNode;

/**
 * 好想直接把链表转成数组来做，但是这样显然不符合面试官的要求
 * 求链表的中位数可以用快慢指针的方法
 */
public class SortedListToBST109 {
    public TreeNode sortedListToBST(ListNode head) {
        return buildTree(head, null);
    }

    private TreeNode buildTree(ListNode left, ListNode right) {
        // left == right 即是左闭右开
        if (left == right)
            return null;
        ListNode mid = getMidNode(left, right);
        TreeNode root = new TreeNode(mid.val);
        root.left = buildTree(left, mid);
        root.right = buildTree(mid.next, right);
        return root;
    }

    private ListNode getMidNode(ListNode left, ListNode right) {
        ListNode fast = left;
        ListNode slow = left;
        while (fast != right && fast.next != right) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
