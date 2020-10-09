package medium;

public class swapPairs {
    /**
     * 想到用栈和三个指针来做这道题
     * @param head
     * @return
     */
    public ListNode mySolution_swapPairs(ListNode head) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode preNode = dummyNode;
        while (preNode.next!=null && preNode.next.next!=null){
            ListNode leftNode = preNode.next;
            ListNode rightNode = leftNode.next;

            leftNode.next = rightNode.next;
            rightNode.next = leftNode;
            preNode.next = rightNode;

            preNode = preNode.next.next;
        }

        return dummyNode.next;
    }
}
