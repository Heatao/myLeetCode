package CodingInterviews;

import test.ListNode;

public class Offer52 {
    /**
     * 之前做过，现在唯一的印象就是曾经做过orz
     * 目前的想法是先遍历两个链表拿到其长度
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        int lenA = 0, lenB = 0;
        ListNode node1 = headA;
        ListNode node2 = headB;
        while (node1 != null) {
            node1 = node1.next;
            lenA++;
        }
        while (node2 != null) {
            node2 = node2.next;
            lenB++;
        }
        node1 = headA;
        while (lenA > lenB) {
            node1 = node1.next;
            lenA--;
        }
        node2 = headB;
        while (lenB > lenA) {
            node2 = node2.next;
            lenB--;
        }
        while (node1 != null) {
            if (node1 == node2) return node1;
            node1 = node1.next;
            node2 = node2.next;
        }
        return null;
    }

    /**
     * 很有爱的一种解法，我走过你的路，你走过我的路，浪漫相遇
     */
    public ListNode others_getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while (A != B) {
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }
}
