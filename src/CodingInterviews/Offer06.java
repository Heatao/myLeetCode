package CodingInterviews;

import test.ListNode;
import java.util.LinkedList;

public class Offer06 {
    /**
     * 这道题还可以用栈做，效率会高一点
     */
    public int[] reversePrint(ListNode head) {
        LinkedList<Integer> list = new LinkedList<>();
        ListNode node = head;
        while (node != null) {
            list.addFirst(node.val);
            node = node.next;
        }
        // 如果是转包装类型的数组可以直接Integer[] array = list.toArray(new Integer[list.size()]);
        // 但是基础类型的数组要么for循环，要么用stream
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
