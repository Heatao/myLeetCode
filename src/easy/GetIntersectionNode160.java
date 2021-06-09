package easy;

/**
 * 你能否设计一个时间复杂度 O(n) 、仅用 O(1) 内存的解决方案？
 * 虽然思考稍微久了一点，但还是想出来了，比用哈希表存舒服
 */
public class GetIntersectionNode160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        int lenA = 0, lenB = 0;
        while (nodeA != null) {
            lenA++;
            nodeA = nodeA.next;
        }
        while (nodeB != null) {
            lenB++;
            nodeB = nodeB.next;
        }

        if (lenA > lenB)
            headA = moveLonger(headA, lenA - lenB);
        else headB = moveLonger(headB, lenB - lenA);

        while (headA != null && headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    private ListNode moveLonger(ListNode head, int step) {
        for (int i = 0; i < step; i++) {
            head = head.next;
        }
        return head;
    }
}
