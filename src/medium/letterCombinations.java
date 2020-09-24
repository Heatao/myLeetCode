package medium;

import java.util.*;

/**
 * LeetCode17.电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class letterCombinations {
    /**
     * 返回所有组合，那么必然遍历所有，无法去除冗余，问题是当字符串长度不确定时，是无法用多个for循环直接嵌套的
     * 当我尝试用多个for循环的时候，发现需要去删除之前存在的字符串
     * 其实当每次都利用之前在ansList存在的字符串的时候，就应该想到用回溯，也就是递归
     * 貌似也可以用队列
     * 最后用队列解决了
     * @param digits
     * @return
     */
    public static List<String>  mySolution_letterCombinations(String digits) {
        Map<Character, String[]> numberMap = new HashMap<>();
        numberMap.put('2', new String[]{"a", "b", "c"});
        numberMap.put('3', new String[]{"d", "e", "f"});
        numberMap.put('4', new String[]{"g", "h", "i"});
        numberMap.put('5', new String[]{"j", "k", "l"});
        numberMap.put('6', new String[]{"m", "n", "o"});
        numberMap.put('7', new String[]{"p", "q", "r", "s"});
        numberMap.put('8', new String[]{"t", "u", "v"});
        numberMap.put('9', new String[]{"w", "x", "y", "z"});

        List<String> ansList = new LinkedList<>();
        //i用于循环digits字符串。j用于循环当前字符对应的英文字母列表
        for (int i = 0; i < digits.length(); i++) {
            int nowLen = ansList.size();
            for (int j = 0; j < numberMap.get(digits.charAt(i)).length; j++) {
                if (i==0) {
                    ansList.add(numberMap.get(digits.charAt(0))[j]);
                }
                //k用于循环之前存在的字符串
                for (int k = 0; k < nowLen; k++) {
                    ansList.add(ansList.get(k) + numberMap.get(digits.charAt(i))[j]);
                }
            }
            //用队列把之前的数删除
            if (nowLen > 0) {
                ansList.subList(0, nowLen).clear();
            }
//            for (int oldIndex=0; oldIndex < nowLen; oldIndex++){
//                ansList.remove(0);
//            }
        }
        return ansList;
    }

    /**
     * 有同学提到，看见"所有组合"的字样，就应该想到回溯。
     * 回溯算法用于寻找所有的可行解，如果发现一个解不可行，则会舍弃不可行的解。
     * 在这道题中，由于每个数字对应的每个字母都可能进入字母组合，因此不存在不可行的解，直接穷举所有的解即可。
     * @param digits
     * @return
     */
    public List<String> official_letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);                    //我觉得这句才是关键啊！
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(mySolution_letterCombinations("23"));
    }
}
