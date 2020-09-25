package medium;

import java.util.HashMap;
import java.util.Map;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

/**
 * LeetCode19.删除倒数第n个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 保证n是有效的
 */
public class removeNthFromEnd {
    /**
     * 要求一遍实现，而且还不是用的ArrayList或者LinkedList，那就只能Hash了
     * @param head
     * @param n
     * @return
     */
    public ListNode mySolution_removeNthFromEnd(ListNode head, int n) {
        Map<Integer, ListNode> nodeMap = new HashMap<>();
        ListNode tmp = head;
        int i=1;
        while (tmp != null){
            nodeMap.put(i, tmp);
            i++;
            tmp = tmp.next;
        }
        //i最后等于len+1,删除key为i-n的节点，这里需要注意是否越过边界的问题
        if (nodeMap.containsKey(i-n-1) && nodeMap.containsKey(i-n+1)){
            nodeMap.get(i-n-1).next = nodeMap.get(i-n+1);
            return head;
        }
        else if (!nodeMap.containsKey(i-n-1) && nodeMap.containsKey(i-n+1)){
            return nodeMap.get(i-n+1);
        }
        else if (nodeMap.containsKey(i-n-1) && !nodeMap.containsKey(i-n+1)){
            nodeMap.get(i-n-1).next = null;
            return head;
        }
        else
            return null;
    }

    /**
     * 双指针一次遍历
     * 本来想到了用双指针，但没想到固定间隔的事
     * dummyNode也很精妙
     * @param head
     * @param n
     * @return
     */
    public ListNode official_removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}
