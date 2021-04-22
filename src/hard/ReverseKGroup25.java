package hard;

import java.awt.*;

/**
 * 20210422坐公交看到脉脉上面说字节面试这道题，在车上自己琢磨（加百度），想到三种做法
 * 1. 先遍历一遍链表得到长度，写一个反转函数(cur, k)，链表前面超过k的部分直接反转
 * 2. 不需先遍历一遍，反转函数(curpre, lat)，传入应该反转的部分的前一个节点和后一个节点，主函数用for循环判断长度是否为k。来自coordinate_blog
 * 3. 递归反转链表，因为链表是一种兼具递归和迭代性质的数据结构。这个做法其实和2一样，也需要latter节点
 *
 * 这个题的难点在于，第一次反转k个之后，preNode，cur应该变成什么，因为反转之后会改变哒！要么用retain来保留，要么返回一个List
 */
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

    public ListNode reverseKGroup_try0422(ListNode head, int k) {
        ListNode preNode = new ListNode(-1);
        preNode.next = head;
        ListNode hair = preNode;
        ListNode latNode;
        ListNode cur = head;

        int i = 1;
        while (cur != null) {
            if (i == k) {
                latNode = cur.next;
                preNode = reverse0422(preNode, latNode);
                //preNode = cur;    //这里极易出错，因为cur此时已经被变换了
                cur = latNode;
                i = 1;
                continue;
            }
            cur = cur.next;
            i++;
        }
        return hair.next;
    }

    private ListNode reverse0422(ListNode preNode, ListNode latNode) {
        ListNode cur = preNode.next;
        ListNode retainNext;
        ListNode tail = latNode;

        ListNode newPre = null;
        while (cur != latNode) {
            if (newPre == null)
                newPre = cur;

            retainNext = cur.next;
            cur.next = tail;
            tail = cur;
            cur = retainNext;
        }
        preNode.next = tail;
        return newPre;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ReverseKGroup25 reverseKGroup25 = new ReverseKGroup25();
        head = reverseKGroup25.reverseKGroup_try0422(head, 2);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
