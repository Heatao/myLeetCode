package medium;

import java.util.LinkedList;
import java.util.List;

public class ReorderList143 {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;
        List<ListNode> nodesList = new LinkedList<>();
        ListNode node = head;
        while (node != null) {
            nodesList.add(node);
            node = node.next;
        }

        node = head;
        nodesList.remove(0);
        int tag = 1;
        while (!nodesList.isEmpty()) {
            if (tag == 1) {
                tag = 0;
                node.next = nodesList.remove(nodesList.size()-1);
            }
            else {
                tag = 1;
                node.next = nodesList.remove(0);
            }
            node = node.next;
        }

        if (node != null) node.next = null;
    }
}
