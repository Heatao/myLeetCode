package easy;

public class DeleteDuplicates83 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode prev = new ListNode(-101, head);
        ListNode cur = head;
        ListNode dummy = prev;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                //下面是必须添加的，因为while的跳出条件是cur.next
                //而且更新步骤是在else里面，所以最后一个node不会被添加到
                if (cur.next == null) {
                    prev.next = cur;
                }
            }
            else {
                prev.next = cur;
                prev = prev.next;
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);

        DeleteDuplicates83 deleteDuplicates83 = new DeleteDuplicates83();
        ListNode listNode = deleteDuplicates83.deleteDuplicates(head);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
