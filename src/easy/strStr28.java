package easy;

/**
 * LeetCode28.实现strStr函数
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。
 *
 * 说明:
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */
public class strStr28 {
    public int mySolution_strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int needleLen = needle.length(), haystackLen = haystack.length();

        for (int i = 0; i < haystackLen - needleLen + 1; i++) {
            //i跳到下一个首字母相同
            while (haystack.charAt(i) != needle.charAt(0)){
                i++;
                if (i >= haystackLen - needleLen + 1) return -1;
            }
//            int hayIndex = i;
//            int needleIndex = 0;
//            while (needleIndex < needleLen && hayIndex < haystackLen){
//                if (haystack.charAt(hayIndex) != needle.charAt(needleIndex)){
//                    break;
//                }
//                needleIndex ++;
//                hayIndex ++;
//            }
//            //判断是否符合条件的if
//            if (needleIndex == needleLen) return i;
            //这里自己本来打算一个字符一个字符进行比较，但是会超时
            if (haystack.substring(i, i + needleLen).equals(needle)){
                return i;
            }
        }
        return -1;
    }

    /**
     * kmp算法
     * 参考：
     * https://leetcode-cn.com/problems/implement-strstr/solution/bang-ni-ba-kmpsuan-fa-xue-ge-tong-tou-ming-ming-ba/
     * https://www.cnblogs.com/yjiyjige/articles/3263858.html
     * https://blog.csdn.net/starstar1992/article/details/54913261/
     */
    public int kmp(String haystack, String needle) {
        if (needle.length() == 0) return 0;

        char[] haystackList = haystack.toCharArray();
        char[] needleList = needle.toCharArray();
        int[] next = getNext(needle);
        int j=-1;   // 因为next数组里记录的起始位置为-1
        for (int i = 0; i < haystack.length(); i++) { // 注意i就从0开始
            while(j >= 0 && haystackList[i] != needleList[j+1]){ // 不匹配
                j = next[j]; // j 寻找之前匹配的位置
            }
            if (haystackList[i] == needleList[j+1]){ // 匹配，j和i同时向后移动
                j++; // i的增加在for循环里
            }
            if (j==(needleList.length-1)) { // 文本串s里出现了模式串t
                return i-needleList.length+1;
            }
        }
        return -1;
    }

    public int[] getNext(String ps) {
        char[] psList = ps.toCharArray();
        int[] next = new int[psList.length];
        int j = -1;
        next[0] = j;
        for (int i = 1; i < psList.length; i++) {       //从1开始，因为0已初始化为-1
            while(j >= 0 && psList[i] != psList[j+1]){  // 前后缀不相同了
                j = next[j];
            }
            if(psList[i] == psList[j+1]){   // 找到相同的前后缀
                j++;
            }
            next[i] = j;    // 将j（前缀的长度）赋给next[i]
        }
        return next;
    }
}
