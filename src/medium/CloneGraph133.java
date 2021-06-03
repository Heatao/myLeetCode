package medium;

import test.Node;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class CloneGraph133 {
    public Node cloneGraph(Node node) {
        if (node == null)
            return null;
        // 因为值和索引有关，所以可以直接存一个hash记录当前节点是否已经重建
        Node newNode = new Node(node.val);

        Queue<Node> que1 = new LinkedList<>();
        que1.offer(node);

        Hashtable<Integer, Node> hashtable = new Hashtable<>();
        hashtable.put(newNode.val, newNode);

        while (!que1.isEmpty()) {
            Node curNode = que1.poll();
            Node preNode = hashtable.get(curNode.val);
            for (Node eachNode : curNode.neighbors) {
                Node nextNewNode;
                if (!hashtable.containsKey(eachNode.val)) {
                    nextNewNode = new Node(eachNode.val);
                    hashtable.put(eachNode.val, nextNewNode);
                    que1.offer(eachNode);
                }
                else nextNewNode = hashtable.get(eachNode.val);
                preNode.neighbors.add(nextNewNode);
            }
        }
        return newNode;
    }
}
