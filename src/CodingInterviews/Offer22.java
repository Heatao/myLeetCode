package CodingInterviews;

import test.ListNode;

/**
 * 我的想法是保留头和尾，让尾指向头，让指针再走len-k
 * 看了眼评论，很精妙，双指针
 */
public class Offer22 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || k == 0) return head;
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k && fast != null; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
