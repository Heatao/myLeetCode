package medium;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 */
public class RotateRight61 {
    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, medium.ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 我朴素的想法：先连成一个环，然后确定最后一位断开，返回新的head
     * i=1那里把我搞了好久，实战的话，需要仔细对照样例分析
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k==0)
            return head;

        int len=1;
        ListNode nowNode = head;
        while (nowNode.next != null) {
            nowNode = nowNode.next;
            len++;
        }
        nowNode.next = head;

        ListNode newTail = head;
        k = k % len;
        for (int i = 1; i < len-k; i++) {
            newTail = newTail.next;
        }
        head = newTail.next;
        newTail.next = null;
        return head;
    }
}
