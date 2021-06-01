package test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFSTemplate {
    // 计算从起点 start 到终点 target 的最近距离
    public int BFS(Node start, Node target) {
        Queue<Node> q = new LinkedList<>(); // 核心数据结构
        Set<Node> visited = new HashSet<>(); // 避免走回头路

        q.offer(start); // 将起点加入队列
        visited.add(start);
        int step = 0; // 记录扩散的步数

        while (!q.isEmpty()) {
            int sz = q.size();
            /* 将当前队列中的所有节点向四周扩散 */
            for (int i = 0; i < sz; i++) {
                Node cur = q.poll();
                /* 划重点：这里判断是否到达终点 */
                if (cur == target)
                    return step;
                /* 将 cur 的相邻节点加入队列 */
                for (Node x : cur.adj)
                    if (!visited.contains(x)) {
                        q.offer(x);
                        visited.add(x);
                    }
            }
            /* 划重点：更新步数在这里 */
            step++;
        }
        return step;
    }

    /*
     * BFS模板：
     * 条件判断（边界判断，其他要求的判断）
     *
     * 创建队列
     *
     * 在队列中加入第一个满足条件的元素
     *
     * while(队列不为空) {
     *     取出队列头部元素
     *     操作
     *     根据头部元素，往队列中再次加入满足条件的元素
     * }
     */

    /*
     * DFS模板：
     * dfs出口，不满足条件就退出
     *
     * 操作
     *
     * 递归，接着进一步DFS
     */
}
