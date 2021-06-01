package hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FindLadders126 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        List<List<String>> paths = new ArrayList<>();
        if (!wordSet.contains(endWord))
            return paths;
        return null;
    }
}
