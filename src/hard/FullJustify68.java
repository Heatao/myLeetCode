package hard;

import java.util.ArrayList;
import java.util.List;

public class FullJustify68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ansList = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        List<String> oneLine = new ArrayList<>();
        int oneLineLen = 0;
        for (int i = 0; i < words.length; i++) {
            if (oneLineLen != 0)
                oneLineLen += 1;

            oneLineLen += words[i].length();
            if (oneLineLen > maxWidth) {
                //把目前的sb给放入List中
                if (oneLine.size() == 1) {
                    sb.append(oneLine.get(0));
                    while (sb.length() < maxWidth) {
                        sb.append(" ");
                    }
                }
                else if (oneLine.size() == 2) {
                    sb.append(oneLine.get(0));
                    for (int j = 0; j < maxWidth-(oneLine.get(0).length() + oneLine.get(1).length()); j++) {
                        sb.append(" ");
                    }
                    sb.append(oneLine.get(1));
                }
                else {
                    //一般情况
                    int wordCnt = oneLine.size();
                    int blank = maxWidth;
                    for (int j = 0; j < wordCnt; j++) {
                        blank -= oneLine.get(j).length();
                    }
                    int mod = blank % (wordCnt-1);
                    int bsn = blank / (wordCnt-1);
                    for (int j = 0; j < wordCnt-1; j++) {
                        sb.append(oneLine.get(j));
                        int k = bsn + (mod > 0 ? 1: 0);
                        mod--;
                        for (int l = 0; l < k; l++) {
                            sb.append(" ");
                        }
                    }
                    sb.append(oneLine.get(wordCnt-1));
                }
                //放入ansList
                ansList.add(sb.toString());

                //把当前的words作为新的line开头
                oneLine = new ArrayList<>();
                oneLine.add(words[i]);
                oneLineLen = words[i].length();
                sb = new StringBuffer();
            }
            else {
                oneLine.add(words[i]);
            }
        }
        //处理最后一行
        if (oneLine.size() != 0) {
            int i = 0;
            while (sb.length() < maxWidth) {
                if (i != 0)
                    sb.append(" ");
                if (i < oneLine.size())
                    sb.append(oneLine.get(i));
                i++;
            }
            ansList.add(sb.toString());
        }
        return ansList;
    }

    public static void main(String[] args) {
        FullJustify68 fullJustify68 = new FullJustify68();
        String[] list = {"This", "is", "an", "example", "of", "text", "justification."};
        for (String each : fullJustify68.fullJustify(list, 16)) {
            System.out.println(each);
        }
    }
}
