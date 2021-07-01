package CodingInterviews;

import java.util.ArrayList;
import java.util.List;

public class Offer62 {
    // 朴素的想法，放到一个List，模拟来做
    // 为什么ArrayList不超时？来自sweetieeyi
    // 下面用ArrayList来做，因为LinkedList虽然删除的效率高，但是！找到删除节点仍然需要O(n)
    // ArrayList 的 remove 操作在后续移位的时候，其实是内存连续空间的拷贝的！所以相比于LinkedList大量非连续性地址访问，ArrayList的性能是很 OK 的！
    public int lastRemaining(int n, int m) {
        int nowIndex = 0;
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(i);
        }
        while (nums.size() > 1) {
            int deleteIndex = (nowIndex + m - 1) % nums.size();
            nowIndex = deleteIndex;
            nums.remove(deleteIndex);
        }

        return nums.get(0);
    }

    public static void main(String[] args) {
        Offer62 offer62 = new Offer62();
        int n = 10;
        int m = 17;
        System.out.println(offer62.lastRemaining(n, m));
    }
}
