package hard;

import java.util.List;

public class ReverseKGroup25 {
    /**
     * 心态崩了，感觉下面的代码应该也有bug，但是OJ超时，逻辑和官方题解是一样的
     * @param head
     * @param k
     * @return
     */
    public ListNode mySolution_reverseKGroup(ListNode head, int k) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode preNode = dummyNode;
        while (preNode.next != null){
            ListNode[] reverse= swapKNode(preNode, preNode.next, k);
            preNode.next = reverse[1];
            preNode = reverse[0];
        }
        return dummyNode.next;
    }

    private ListNode moveNode(ListNode thisNode, int n) {
        int i=0;
        while(thisNode != null && i<n){
            i++;
            thisNode = thisNode.next;
        }
        return thisNode;
    }

    /**
     * 长度为k的单链表直接逆序
     * @param thisNodePrev 需要逆序的部分的前一个结点
     * @param thisNode 链表头，注意链表没有结点
     * @param k 链表需要逆序的长度
     * @return 新的链表头
     * 参考https://www.jianshu.com/p/43ff77f0da95
     */
    private ListNode[] swapKNode(ListNode thisNodePrev, ListNode thisNode, int k) {
        ListNode prev = thisNodePrev;
        ListNode head = thisNode;
        ListNode tail = thisNode;
        ListNode next;
        if (thisNode == null || moveNode(thisNode, k) == null) return new ListNode[] {thisNode, null};

        int i=0;
        //这里head!=null应该是用不上的
        while (head != null && i<k) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        //这里因为会报错，所以参考了官方题解的做法，把tail也返回
        return new ListNode[] {prev, tail};
    }

    /**
     * 官方题解，时间没问题的
     * @param head
     * @param k
     * @return
     */
    public ListNode official_reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }

    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }
}
