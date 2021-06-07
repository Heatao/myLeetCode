package medium;

import java.util.*;

/**
 * 新数据插入到链表头部；
 * 每当缓存命中（即缓存数据被访问），则将数据移到链表头部；
 * 当链表满的时候，将链表尾部的数据丢弃。
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * 下面貌似没有达到O(1)喔，因为没有官方题解快
 */
public class LRUCache146 {

    private int capacity;
    private LinkedHashMap<Integer, Integer> LRULinks;

    public LRUCache146(int capacity) {
        this.capacity = capacity;
        this.LRULinks = new LinkedHashMap<>(capacity);
    }

    public int get(int key) {
        if (this.LRULinks.containsKey(key)) {
            int oldValue = this.LRULinks.remove(key);
            this.LRULinks.put(key, oldValue);
            return LRULinks.get(key);
        }
        else return -1;
    }

    public void put(int key, int value) {
        if (this.LRULinks.containsKey(key)) {
            this.LRULinks.remove(key);
        }
        else if (this.LRULinks.size() == this.capacity) {
            int oldKey = this.LRULinks.keySet().iterator().next();
            this.LRULinks.remove(oldKey);
        }

        this.LRULinks.put(key, value);
    }

}
