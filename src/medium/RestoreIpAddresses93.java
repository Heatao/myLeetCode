package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 这种返回list的，大概率不是DP而是回溯
 *
 * 需要注意的：
 * 1. IP的规范：不可0开头，不可大于255
 * 2. substring是左闭右开，所以前面的if需要<=
 * 3. 剪枝优化
 */
public class RestoreIpAddresses93 {
    public List<String> restoreIpAddresses(String s) {
        List<String> combinations = new ArrayList<>();
        if (s.length() > 12 || s.length() < 4)
            return combinations;

        List<String> tempList = new ArrayList<>();
        backtrack(combinations, tempList, 0, s);
        return combinations;
    }

    /**
     * 添加ip的时候还需要注意ip的规范
     */
    private void backtrack(List<String> combinations, List<String> tempList, int index, String s) {
        if (index >= s.length()) {
            if (tempList.size() == 4 && index == s.length()) {
                String ip = String.join(".", tempList);
                if (ip.length() == s.length()+3)
                    combinations.add(ip);
            }
            return;
        }
        if (tempList.size() == 4)
            return;

        String one;
        for (int i = index; i < s.length(); i++) {
            one = String.valueOf(s.charAt(i));
            tempList.add(one);
            backtrack(combinations, tempList, i + 1, s);
            tempList.remove(tempList.size() - 1);

            if (s.charAt(index) == '0')
                return;

            if (i+2 <= s.length()) {
                one = s.substring(i, i+2);
                tempList.add(one);
                backtrack(combinations, tempList, i + 2, s);
                tempList.remove(tempList.size() - 1);
            }

            if (i+3 <= s.length()) {
                one = s.substring(i, i+3);
                if (Integer.parseInt(one) > 255)
                    continue;
                tempList.add(one);
                backtrack(combinations, tempList, i + 3, s);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        RestoreIpAddresses93 restoreIpAddresses93 = new RestoreIpAddresses93();
        String s = "101023";
        System.out.println(restoreIpAddresses93.restoreIpAddresses(s));
    }
}
