package hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WordBreak140 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        List<String> ans = new ArrayList<>();
        if (len == 0 || wordDict.size() == 0)
            return ans;
        List<String> tmp = new ArrayList<>();
        HashSet<String> wordSet = new HashSet<>(wordDict);
        backTrack(s, wordSet, ans, tmp, 0);
        return ans;
    }

    private void backTrack(String s, HashSet<String> wordSet, List<String> ans, List<String> tmp, int index) {
        if (index >= s.length()) {
            ans.add(String.join(" ", tmp));
        }

        for (int i = index + 1; i <= s.length(); i++) {
            String thisSub = s.substring(index, i);
            if (wordSet.contains(thisSub)) {
                tmp.add(thisSub);
                backTrack(s, wordSet, ans, tmp, i);
                tmp.remove(tmp.size()-1);
            }
        }
    }
}
