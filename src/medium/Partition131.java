package medium;

import java.util.ArrayList;
import java.util.List;

public class Partition131 {
    public List<List<String>> partition(String s) {
        List<List<String>> combinations = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        backtrack(combinations, tempList, 0, s);
        return combinations;
    }

    private void backtrack(List<List<String>> combinations, List<String> tempList, int index, String s) {
        if (index >= s.length()) {
//            if (String.join("", tempList).length() == s.length())
//                combinations.add(new ArrayList<>(tempList));
            combinations.add(new ArrayList<>(tempList));
            return;
        }

        /**
         * 这里可以进行优化
         */
        /*
        for (int i = index; i < s.length(); i++) {
            for (int j = i+1; j <= s.length(); j++) {
                if (judgePalindrome(s.substring(i, j))) {
                    tempList.add(s.substring(i, j));
                    backtrack(combinations, tempList, j, s);
                    tempList.remove(tempList.size()-1);
                }
            }
        }
        */
        for (int i = index; i < s.length(); i++) {
            if (judgePalindrome(s.substring(index, i + 1))) {
                tempList.add(s.substring(index, i + 1));
                backtrack(combinations, tempList, i+1, s);
                tempList.remove(tempList.size()-1);
            }
        }
    }

    private boolean judgePalindrome(String s1) {
//        System.out.println(s1);
        StringBuilder s = new StringBuilder(s1);
        String s2 = s.reverse().toString();
        return s1.equals(s2);
    }

    public static void main(String[] args) {
        Partition131 partition131 = new Partition131();
        String s = "aab";
        System.out.println(partition131.partition(s).toString());
    }
}
