package easy;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

/**
 * LeetCode21.合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class mergeTwoLists {
    public ListNode mySolution_mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode();
        ListNode tmpNode = dummyNode;
        while (l1!=null && l2!=null){
            if (l1.val <= l2.val){
                tmpNode.next = l1;
                tmpNode = tmpNode.next;
                l1 = l1.next;
            }
            else {
                tmpNode.next = l2;
                tmpNode = tmpNode.next;
                l2 = l2.next;
            }
        }
        tmpNode.next = l1 == null ? l2 : l1;
        return dummyNode.next;
    }

    /**
     * 也可以用递归的方式去做，递归的空间复杂度更高
     * 并且需要恢复函数栈
     * 空间复杂度：O(n + m)O(n+m)，其中 nn 和 mm 分别为两个链表的长度。
     * 递归调用 mergeTwoLists 函数时需要消耗栈空间，栈空间的大小取决于递归调用的深度。
     * 结束递归调用时 mergeTwoLists 函数最多调用 n+mn+m 次，因此空间复杂度为 O(n+m)O(n+m)
     *
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        //...
        return null;
    }

    public ListNode official_mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }
}
