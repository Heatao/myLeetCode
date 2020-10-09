package hard;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

/**
 * LeetCode23.合并k个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 */
public class mergeKLists {
    /**
     * 之前有个题是合并两个升序链表，这时候只需要两层for循环就可以了，类比这个题，我的第一反应是用回溯来解决不知道多少个for循环的问题
     * 第二反应是用TreeMap，遍历每一个list存储每个数出现的次数，但是这样升序就没有用了
     * 后面想到直接按列循环不久可以了吗
     * 后来发现这个思路是优先队列的，但是直接这样做不行
     * @param lists
     * @return
     */
    public ListNode mySolution_mergeKLists(ListNode[] lists) {
        ListNode ansList = new ListNode();
        ListNode nowLeft = ansList;
        while(true){
            int tagNull = 0;
            int thisMin = Integer.MAX_VALUE;
            int thisIndex = 0;
            for (int i=0; i < lists.length; i++) {
                if (lists[i]!=null && lists[i].val < thisMin){
                    thisMin = lists[i].val;
                    thisIndex = i;
                    tagNull = 1;
                }
            }
            if(tagNull == 0) break;

            nowLeft.next = lists[thisIndex];
            nowLeft = nowLeft.next;
            lists[thisIndex] = lists[thisIndex].next;
        }
        nowLeft.next = null;
        return ansList.next;
    }

    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }

    PriorityQueue<Status> queue = new PriorityQueue<Status>();

    /**
     * 官方是用一个优先队列来实现的
     * @param lists
     * @return
     */
    public ListNode official_mergeKLists(ListNode[] lists) {
        for (ListNode node: lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }
}
