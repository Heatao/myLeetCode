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

    /**
     * 第二次做竟然一次做对了
     */
    private ListNode do2nd(ListNode head, int left, int right) {
        // 这里貌似是从1开始计算的喔
        ListNode hair = new ListNode(-501);
        hair.next = head;
        ListNode prev = hair, cur = head;
        for(int i = 1; i < left; i++) {
            prev = prev.next;
            cur = cur.next;
        }
        // 找到原本的next
        ListNode retainNext = cur;
        for(int i = 0; i < right - left + 1; i++) {
            retainNext = retainNext.next;
        }

        // 开始反转，这里可以用一个列表保存下来
        ListNode curNext;
        for(int i = 0; i < right - left + 1; i++) {
            curNext = cur.next;
            cur.next = retainNext;
            retainNext = cur;
            prev.next = cur;                    // 每次都更新
            cur = curNext;
        }
        return hair.next;
    }
}
