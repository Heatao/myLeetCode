package medium;

/**
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * 迷惑的描述,，其实两个分区是在暗示用两个链表
 */
public class Partition86 {
    //如果头节点是没有值的
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null)
            return head;
        ListNode newPart = new ListNode(-101);
        ListNode newPartHead = newPart;
        ListNode cur = head;
        ListNode first = null;
        while (cur.next != null) {
            if (cur.next.val < x) {
                newPart.next = cur.next;
                newPart = newPart.next;
                cur.next = cur.next.next;
            }
            else if (first == null) {
                first = cur.next;
                cur = cur.next;
            }
            else cur = cur.next;
        }
        newPart.next = first;
        return newPartHead.next;
    }

    //如果头节点是有值的
    public ListNode partition_head(ListNode head, int x) {
        if (head == null || head.next == null)
            return head;
        ListNode newPart = new ListNode(-101);
        ListNode newPartHead = newPart;

        ListNode cur = new ListNode(-101);
        cur.next = head;

        ListNode first = null;
        while (cur.next != null) {
            if (cur.next.val < x) {
                newPart.next = cur.next;
                newPart = newPart.next;
                cur.next = cur.next.next;
            }
            else if (first == null) {
                first = cur.next;
                cur = cur.next;
            }
            else cur = cur.next;
        }
        newPart.next = first;
        return newPartHead.next;
    }

    public static void main(String[] args) {
        Partition86 partition86 = new Partition86();

        ListNode head = new ListNode(0);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);

        ListNode newhead = partition86.partition(head, 2);
        while (newhead.next != null) {
            System.out.println(newhead.next.val);
            newhead = newhead.next;
        }
    }
}
