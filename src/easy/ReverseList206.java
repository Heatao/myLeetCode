package easy;

/**
 * Iterative or recursive
 */
public class ReverseList206 {
    public ListNode reverseListIterative(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = head;
        ListNode cur = head.next;
        head.next = null;
        while (cur != null) {
            ListNode nextNode = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextNode;
        }

        return prev;
    }

    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head.next;
        head.next = null;
        return recursive(head, cur);
    }

    private ListNode recursive(ListNode prev, ListNode cur) {
        if (cur == null) return prev;
        ListNode next = cur.next;
        cur.next = prev;
        return recursive(cur, next);
    }

    public static void main(String[] args) {
        ReverseList206 reverseList206 = new ReverseList206();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(2);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;
        reverseList206.reverseListRecursive(node1);
    }
}
