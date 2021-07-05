package easy;

public class HasCycle141 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null)
                return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    // 第二次做，还是有细节没处理到位
    public boolean do2nd(ListNode head) {
        if(head == null) return false;
        ListNode fast = head.next, slow = head;                         // 注意fast这里
        while(fast != null && fast.next != null) {
            if(fast == slow) return true;
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

    public static void main(String[] args) {
        HasCycle141 hasCycle141 = new HasCycle141();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = head;
        System.out.println(hasCycle141.do2nd(head));
    }
}
