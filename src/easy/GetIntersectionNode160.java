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

    public ListNode do2nd(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        // 我踏遍每一寸土地，只为了寻找你
        int lenA = 0, lenB = 0;
        ListNode node1 = headA, node2 = headB;
        while(node1 != null) {
            lenA++;
            node1 = node1.next;
        }
        while(node2 != null) {
            lenB++;
            node2 = node2.next;
        }

        node1 = headB;
        node2 = headA;
        while(lenA > lenB) {
            node2 = node2.next;
            lenA--;
        }
        while(lenB > lenA) {
            node1 = node1.next;
            lenB--;
        }

        while(node1 != null) {
            if(node1 == node2) return node1;
            node1 = node1.next;
            node2 = node2.next;
        }

        return null;
    }
}
