package CodingInterviews;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

// 其实用一个哈希表就完事儿了，不需要两个哈希set
public class Offer50 {
    // 用两个哈希set就行了
    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0) return ' ';
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        Set<Character> dupSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char thisChar = s.charAt(i);
            if (!set.contains(thisChar) && !dupSet.contains(thisChar)) set.add(thisChar);
            else {
                set.remove(thisChar);
                dupSet.add(thisChar);
            }
        }

        if (set.isEmpty()) return ' ';
        else return set.iterator().next();
    }

    public static void main(String[] args) {
        Offer50 offer50 = new Offer50();
        String s = "aadadaad";
        System.out.println(offer50.firstUniqChar(s));
    }
}
