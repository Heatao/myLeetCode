package medium;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LeetCode3.无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class LengthOfLongestSubstring03 {
    public static int mySolution_lengthOfLongestSubstring(String s) {
        /**
         * 我的思路：类似于LeetCode1题，做一个map，每次只需要判断当前这个字符是否在之前的map存在，不存在，计数+1，
         * 若重复，则map变为当前这个字符，重置计数
         *
         * 知识点记录：
         * 空串""是长度为0的字符串，它有自己的串长度（0）和内容（空）
         * null串表示目前没有任何对象与该变量关联
         *
         * 错误：hashmap没有顺序，使用LinkedHashMap
         */
//        if (s == null) return 0;                  //这么写是错误的，""是空串而不是null串
        if (s == null || s.length()==0) return 0;
        Map<Character, Integer> map = new LinkedHashMap<>();
        int maxNum = 1;                             //这里需要为1，来到这里至少不是空，则最小为1
        int count = 0;
        int delCount = 0;                           //这个用来记录删除了多少位
        char [] c = s.toCharArray();
        for (char cc : c){
            if (map.isEmpty()){
                map.put(cc, 1);
                count = 1;
            }
            else {
                if (map.get(cc) == null){
                    map.put(cc, 1);
                    count += 1;
                    maxNum = Math.max(count, maxNum);
                }
                //效率差在不停的去遍历map置空
                //优化：更快的找到上一个重复的位置
                else {
                    //如果存在，那么看看之前最长多少，并且只置空之前重复的字符及其前面
                    //注意map是没有索引的，需要索引得新建一个ArrayList
//                    map.clear();                      //之前clear有问题，这样丢失了两个重复元素之前的字符
                    for (Iterator<Map.Entry<Character, Integer>> it = map.entrySet().iterator(); it.hasNext();){
                        Map.Entry<Character, Integer> item = it.next();
                        it.remove();
                        delCount += 1;
                        if (item.getKey()==cc) break;
                    }
                    map.put(cc, 1);
                    count = count-delCount+1;
                    delCount = 0;
                }
            }
        }
        return maxNum;
    }

    public static int others_lengthOfLongestSubstring(String s) {
        /**
         * 标签：滑动窗口
         * 暴力解法时间复杂度较高，会达到 O(n^2)，故而采取滑动窗口的方法降低时间复杂度
         * 定义一个 map 数据结构存储 (k, v)，其中 key 值为字符，value 值为字符位置 +1，加 1 表示从字符位置后一个才开始不重复
         * 我们定义不重复子串的开始位置为 start，结束位置为 end
         * 随着 end 不断遍历向后，会遇到与 [start, end] 区间内字符相同的情况，此时将字符作为 key 值，获取其 value 值，并更新 start，此时 [start, end] 区间内不存在重复字符
         * 无论是否更新 start，都会更新其 map 数据结构和结果 ans。
         * 时间复杂度：O(n)
         */
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int maxNum = 1;
        for (int end = 0, start = 0; end < s.length(); end++){
            char cc = s.charAt(end);
            if (map.containsKey(cc)){           //如果存在了，则更新start的位置
                start = Math.max(map.get(cc) + 1, start);
            }
            maxNum = Math.max(end-start+1, maxNum);
            map.put(cc, end);               //屋无论是否存在，都更新当前字符的位置
        }
        return maxNum;
    }

    public static void main(String[] args) {
        String s = "bbtablud";
        System.out.println(mySolution_lengthOfLongestSubstring(s));
        System.out.println(others_lengthOfLongestSubstring(s));
    }
}
