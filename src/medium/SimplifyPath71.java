package medium;

import java.util.Deque;
import java.util.LinkedList;

public class SimplifyPath71 {
    /**
     * 用双端队列来实现，可以用LinkedList
     * 也可以一个字母一个字母遍历，但是这样需要记录每一个路径单词的长度，更麻烦了，还不如直接split，split还能去除多余的'/'
     */
    public String simplifyPath(String path) {
        Deque<String> pathQueue = new LinkedList<>();
        String[] pathList = path.split("/");
        for (String eachP : pathList) {
            if (eachP.equals(".") || eachP.equals(""))
                continue;
            if (eachP.equals(".."))
                pathQueue.pollLast();
            else pathQueue.offerLast(eachP);
        }

        StringBuilder sb = new StringBuilder();
        while (!pathQueue.isEmpty()) {
            sb.append('/');
            sb.append(pathQueue.pollFirst());
        }
        if (sb.toString().length() > 0)
            return sb.toString();
        else return "/";
    }

    public static void main(String[] args) {
        String path = "/../";
        SimplifyPath71 simplifyPath71 = new SimplifyPath71();
        System.out.println(simplifyPath71.simplifyPath(path));
    }
}
