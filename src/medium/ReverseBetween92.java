package medium;

public class ReverseBetween92 {
    /*
    两趟反转的话可以先确定起始点
    一趟的话，在中间反转，会影响next的位置
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head.next == null || left == right)
            return head;
        ListNode lNode = null;
        ListNode rNode = null;
        ListNode tempNodeLast = null;
        ListNode tempNodeFirst = null;

        int index = 1;
        ListNode cur = head;
        while (cur != null) {
            //这样会忽略left为1的情况
            if (index + 1 == left) {
                lNode = cur;
            }
            else if (index == right) {
                rNode = cur.next;
            }

            if (left <= index && index <= right) {
                ListNode retainNext = cur.next;
                cur.next = tempNodeFirst;
                if (tempNodeLast == null)
                    tempNodeLast = cur;
                tempNodeFirst = cur;

                cur = retainNext;
                index++;
                continue;
            }
            index++;
            cur = cur.next;
        }
        if (lNode == null) {
            tempNodeLast.next = rNode;
            return tempNodeFirst;
        }
        else {
            lNode.next = tempNodeFirst;
            tempNodeLast.next = rNode;
        }

        return head;
    }

    public static void main(String[] args) {
        ReverseBetween92 reverseBetween92 = new ReverseBetween92();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode newhead = reverseBetween92.reverseBetween(head, 2, 4);

//        ListNode head = new ListNode(3);
//        head.next = new ListNode(5);
//        ListNode newhead = reverseBetween92.reverseBetween(head, 1, 2);

        while (newhead != null) {
            System.out.println(newhead.val);
            newhead = newhead.next;
        }
    }
}
