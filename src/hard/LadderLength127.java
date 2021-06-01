package hard;

import java.util.*;

public class LadderLength127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord))
            return 0;

        Queue<String> que = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        que.offer(beginWord);
        wordSet.remove(beginWord);

        int step = 1;
        while (!que.isEmpty()) {
            int queLen = que.size();
            for (int i = 0; i < queLen; i++) {
                String currentWord = que.poll();

                char[] currentWordArray = currentWord.toCharArray();
                for (int j = 0; j < endWord.length(); j++) {

                    char retainChar = currentWordArray[j];
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (k == retainChar)
                            continue;
                        currentWordArray[j] = k;
                        // 不能是下面这种写法
                        // String nextWord = Arrays.toString(currentWordArray);
                        String nextWord = String.valueOf(currentWordArray);
                        if (wordSet.contains(nextWord) && !visited.contains(nextWord)) {

                            // 如果等于end的话这里就可以判断了
                            if (nextWord.equals(endWord))
                                return step+1;

                            que.offer(nextWord);
                            visited.add(nextWord);
                        }
                        currentWordArray[j] = retainChar;
                    }
                }
            }
            // 注意这个step的位置，在每一层的最后再加1喔，而不是每一个单词出队列都加1
            step++;
        }
        return 0;
    }

    /**
     * 下面是liweiwei大佬的解法，用双向BFS优化
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int others_ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }

        // 第 2 步：已经访问过的 word 添加到 visited 哈希表里
        Set<String> visited = new HashSet<>();
        // 分别用左边和右边扩散的哈希表代替单向 BFS 里的队列，它们在双向 BFS 的过程中交替使用
        Set<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);
        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);

        // 第 3 步：执行双向 BFS，左右交替扩散的步数之和为所求
        int step = 1;
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            // 优先选择小的哈希表进行扩散，考虑到的情况更少
            if (beginVisited.size() > endVisited.size()) {
                Set<String> temp = beginVisited;
                beginVisited = endVisited;
                endVisited = temp;
            }

            // 逻辑到这里，保证 beginVisited 是相对较小的集合，nextLevelVisited 在扩散完成以后，会成为新的 beginVisited
            Set<String> nextLevelVisited = new HashSet<>();
            for (String word : beginVisited) {
                if (changeWordEveryOneLetter(word, endVisited, visited, wordSet, nextLevelVisited)) {
                    return step + 1;
                }
            }

            // 原来的 beginVisited 废弃，从 nextLevelVisited 开始新的双向 BFS
            beginVisited = nextLevelVisited;
            step++;
        }
        return 0;
    }


    /**
     * 尝试对 word 修改每一个字符，看看是不是能落在 endVisited 中，扩展得到的新的 word 添加到 nextLevelVisited 里
     *
     * @param word
     * @param endVisited
     * @param visited
     * @param wordSet
     * @param nextLevelVisited
     * @return
     */
    private boolean changeWordEveryOneLetter(String word, Set<String> endVisited,
                                             Set<String> visited,
                                             Set<String> wordSet,
                                             Set<String> nextLevelVisited) {
        char[] charArray = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            char originChar = charArray[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (originChar == c) {
                    continue;
                }
                charArray[i] = c;
                String nextWord = String.valueOf(charArray);
                if (wordSet.contains(nextWord)) {
                    if (endVisited.contains(nextWord)) {
                        return true;
                    }
                    if (!visited.contains(nextWord)) {
                        nextLevelVisited.add(nextWord);
                        visited.add(nextWord);
                    }
                }
            }
            // 恢复，下次再用
            charArray[i] = originChar;
        }
        return false;
    }

    public static void main(String[] args) {
        String start = "hit";
        String end = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        LadderLength127 ladderLength127 = new LadderLength127();
        System.out.println(ladderLength127.ladderLength(start, end, Arrays.asList(wordList)));
    }
}
