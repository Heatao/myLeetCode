package hard;

import medium.Change518;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MinWindow76 {
    /*
    策略：
    s从0开始找到包含t的子串，然后左指针向右滑动找到这个子串最小包含t的字符串，记录此时的长度
    再将左指针向右移动一位，此时必不包含t的所有字符，然后右指针右移，再左指针右移，比较此时长度是否为最小
    如何判断包含：用一个哈希表记录其t中字符出现的次数

    下面代码应该有很多可以优化的地方，因为只击败了5.1%
     */
    public String minWindow(String s, String t) {
        int lp = 0;
        int rp = -1;            //因为右指针会先++，如果写为0的话右指针最后和左指针一起++，虽然貌似也可以但是不符合逻辑
        int start = 0;
        int minLen = s.length()+1;              //设置为+1是因为可能s整个才包含t

        //HashMap的比较可以直接用equals，如果key或者value是自定义类，需要重写其equals方法喔
        //但是这道题必须自己写check，因为可能tempHash的数值大于tHash，这也是成立的
        HashMap<Character, Integer> tHash = new HashMap<>();
        for(Character each : t.toCharArray()) {
            if (tHash.containsKey(each)) {
                tHash.put(each, tHash.get(each)+1);
            }
            else tHash.put(each, 1);
        }

        HashMap<Character, Integer> tempHash = new HashMap<>();
        int sLen = s.length();
        while (rp < sLen) {
            rp++;
            if (rp < sLen && tHash.containsKey(s.charAt(rp))) {
                tempHash.put(s.charAt(rp), tempHash.getOrDefault(s.charAt(rp), 0) + 1);
            }
            //此时子串包含了t所以应该右移，然后修改minLen，移出tempHash
            while (check(tHash, tempHash) && lp <= rp) {
                if (tHash.containsKey(s.charAt(lp)))
                    tempHash.put(s.charAt(lp), tempHash.get(s.charAt(lp)) - 1);
                lp++;
                if (minLen > rp - lp + 1) {
                    minLen = rp - lp + 1;
                    start = lp - 1;
                }
                if (!check(tHash, tempHash)) {
                    break;
                }
            }
        }

        if (minLen == s.length()+1) {
            return "";
        }
        return s.substring(start, start + minLen + 1);
    }

    /*
    这里是模仿leetcode的
     */
    private boolean check(HashMap<Character, Integer> tHash, HashMap<Character, Integer> tempHash) {
        Iterator iter = tHash.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (tempHash.getOrDefault(key, 0) < val)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "a";
        String t = "a";
        MinWindow76 minWindow76 = new MinWindow76();
        System.out.println(minWindow76.minWindow(s, t));
        System.out.println(minWindow76.do2nd_minWindow(s, t));
    }

    public String do2nd_minWindow(String s, String t) {
        // 要求O(n)时间
        // 双指针+哈希表，先将t存到一个哈希表作为key，双指针从左到右，但是t中的字符可能存在多个呀
        int lens = s.length();
        int lent = t.length();
        if(lent > lens) return "";
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < lent; i++) {
            Character key = t.charAt(i);
            if(hashMap.containsKey(key)) {
                hashMap.put(key, hashMap.get(key)+1);
            }
            else
                hashMap.put(key, 1);
        }

        int minSize = Integer.MAX_VALUE;
        String ans = "";
        int left = 0;
        char[] slist = s.toCharArray();
        // 直到判断出存在，然后左指针右移
        for(int right = 0; right < lens; right++) {
            Character key = slist[right];
            if(!hashMap.containsKey(key)) continue;
            hashMap.put(key, hashMap.get(key)-1);
            boolean tag = true;
            for(Map.Entry<Character, Integer> entry : hashMap.entrySet()) {
                if (entry.getValue() > 0) {
                    tag = false;
                    break;
                }
            }
            // 此时左移指针
            if (tag) {
                while (left <= right) {
                    Character leftKey = slist[left];                                // 易错点
                    if (hashMap.containsKey(leftKey)) {
                        if (hashMap.get(leftKey) < 0)
                            hashMap.put(leftKey, hashMap.get(leftKey) + 1);
                        else {
                            // 此时可以判断最小值啦，此时left是不可以去掉的
                            if (minSize > right - left + 1){
                                ans = s.substring(left, right + 1);
                                minSize = right - left + 1;
                            }
                            break;
                        }
                    }
                    left++;                                                         // 易错点
                }
            }
        }

        return ans;
    }
}
