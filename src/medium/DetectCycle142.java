package medium;

import java.util.HashSet;
import java.util.Set;

public class DetectCycle142 {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        Set<ListNode> linkSet = new HashSet();
        ListNode node = head;
        while (node != null) {
            if (!linkSet.contains(node))
                linkSet.add(node);
            else return node;
            node = node.next;
        }
        return null;
    }

    // 看了参考之后
    // 一开始快慢最好在一个位置
    // 快指针走过的距离需要-1 公式推导后为a=c-1 所以slow要多走一步 以减少一步距离
    /**
     *
     * slow * 2 = fast;
     * slow = a + b;
     * fast = a + b + c + b = a + 2*b + c;
     * (a + b)*2 = a + 2*b + c;
     * a = c;
     */
    public ListNode others_detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode ansNode = head;
        ListNode slow = head, fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null)
                return null;
            fast = fast.next.next;
            slow = slow.next;
        }

        // 因为一开始不在一位置
        while (ansNode != slow.next) {
            ansNode = ansNode.next;
            slow = slow.next;
        }
        return ansNode;
    }

    /*
    在牛客看到的只用O(1)空间复杂度的做法， 我们new一个结点temp ，
    用一个指针从链表头开始遍历,每次都让链表的next指向temp，这样链表就被拆开了，
    如果没有环就结束到NULL，如果有环那么必然在遇到入口时，
    入口的next已经在之前被指向了temp，判断是否next=temp如果相等，那么这个点就是入口
     */

    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        DetectCycle142 detectCycle142 = new DetectCycle142();
        System.out.println(detectCycle142.do2nd(node1));
    }

    private ListNode do2nd(ListNode head) {
        // 迎接新生命的降临
        if(head == null) return null;
        ListNode father = head;
        ListNode mother = head;
        ListNode baby = head;
        //int len = 0;                                      // 易错点1：因为可能走了不止一圈才遇到，所以len是不准的
        while(father != null && father.next != null) {
            father = father.next.next;
            mother = mother.next;
            if(father == mother) {                          // 易错点2：if判断要放在下面哟
                // 相遇，孩子开始走
                while (mother != baby) {
                    mother = mother.next;
                    baby = baby.next;
                }
                return baby;
            }
        }
        return null;
    }
}
