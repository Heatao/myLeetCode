package medium;

import java.util.ArrayList;
import java.util.List;

public class Combine77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        backtrack(combinations, tempList, 1, n, k);
        return combinations;
    }

    private void backtrack(List<List<Integer>> combinations, List<Integer> tempList, int start, int n, int k) {
        if (tempList.size() == k) {
            combinations.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i < n + 1; i++) {
            tempList.add(i);
            backtrack(combinations, tempList, i + 1, n, k);
            tempList.remove(tempList.size()-1);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        Combine77 combine77 = new Combine77();
        System.out.println(combine77.combine(n, k).toString());
    }
}
