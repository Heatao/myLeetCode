package medium;

public class InsertionSortList147 {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode curNode = head.next;
        ListNode preNode = head;
        int preValue = head.val;
        while (curNode != null) {
            if (curNode.val >= preValue) {
                preValue = curNode.val;
                curNode = curNode.next;
                preNode = preNode.next;
            }
            else if (head.val > curNode.val) {
                preNode.next = curNode.next;
                curNode.next = head;
                head = curNode;
                curNode = preNode.next;
            }
            else {
                ListNode tmpNode = head;
                while (tmpNode.next.val < curNode.val) {
                    tmpNode = tmpNode.next;
                }
                preNode.next = curNode.next;
                curNode.next = tmpNode.next;
                tmpNode.next = curNode;
                curNode = preNode.next;
            }
        }
        return head;
    }
}
