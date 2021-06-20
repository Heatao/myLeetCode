package CodingInterviews;

import test.ListNode;

public class Offer24 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head.next;
        ListNode prev = head;
        while (cur != null) {
            ListNode nextNode = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextNode;
        }
        head.next = null;
        return prev;
    }
}
