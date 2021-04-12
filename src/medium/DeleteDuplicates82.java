package medium;

/**
 * 优秀的答案：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/comments/91315
 */
public class DeleteDuplicates82 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode prev = new ListNode(-101, head);                     //因为范围是-100开始，所以初始化为101
        ListNode point = head;
        while (point != null && point.next != null) {
            if (point.val == point.next.val) {
                while (point.next != null && point.val == point.next.val) {
                    point = point.next;
                }
                //结尾有重复的情况
                //其实是多余的
                if (point.next == null)
                    prev.next = null;
                else
                    //这里会使连续两个重复比如2233中，把3放入链表中，所以在if (point.val == point.next.val)最好不要赋值
                    prev.next = point.next;
                //因为一开始可能就有重复的，head需要重置
                if (prev.val == -101)
                    head = prev.next;
                //如果这里给prev赋值了，那么遇到2233这种情况，会把3放进去
                //prev = prev.next;

                //这一句如果不写，而是把30行写为prev = prev.next，会将2233的3放进去
                //这里显然point.next才是没有重复的部分呀
                point = point.next;
            }
            else {
                prev = point;
                point = point.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);
        DeleteDuplicates82 deleteDuplicates82 = new DeleteDuplicates82();
        ListNode listNode = deleteDuplicates82.deleteDuplicates(head);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
