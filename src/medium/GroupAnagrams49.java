package medium;

import java.util.*;

/**
 * LeetCode49.字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 */
public class GroupAnagrams49 {
    /**
     * 学习了一下双括号初始化集合的方法
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> strDict = new HashMap<>();
        String key;
        for(String str : strs) {
            key = formKey(str);
            if (!strDict.containsKey(key))
                strDict.put(key, new ArrayList<>(){{add(str);}});
            else {
                strDict.get(key).add(str);
            }
        }
        return new ArrayList<>(strDict.values());
    }

    private String formKey(String str) {
        char[] strArray = str.toCharArray();
        Arrays.sort(strArray);
        return Arrays.toString(strArray);
    }
}
