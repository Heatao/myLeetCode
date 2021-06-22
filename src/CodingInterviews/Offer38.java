package CodingInterviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 下次做的时候要剪枝！
 */
public class Offer38 {
    public String[] permutation(String s) {
        ArrayList<String> aims = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Set<Integer> indexList = new HashSet<>();
        Set<String> eleSet = new HashSet<>();
        backtrack(aims, sb, s, indexList, eleSet);
        return (String[]) aims.toArray(new String[aims.size()]);
    }

    // 忽然觉得这道题还是有点难度哒，需要记录一个index数组
    private void backtrack(ArrayList<String> combinations, StringBuilder sb, String s, Set<Integer> indexList, Set<String> eleSet) {
        if (sb.length() == s.length()) {
            if (!eleSet.contains(sb.toString())) {
                eleSet.add(sb.toString());
                combinations.add(sb.toString());
            }
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (indexList.contains(i)) continue;
            indexList.add(i);
            sb.append(s.charAt(i));

            backtrack(combinations, sb, s, indexList, eleSet);

            indexList.remove(i);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public static void main(String[] args) {
        Offer38 offer38 = new Offer38();
        String s1 = "abc";
        String s2 = "aab";
        System.out.println(Arrays.toString(offer38.permutation(s2)));
    }
}
