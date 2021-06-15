package medium;

import java.util.*;

public class FindRepeatedDnaSequences187 {
    /**
     * 最朴素的想法，将每一个子串都存在一个哈希表里，可以利用位运算优化存储，还可以用两个hashset减少一次遍历
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        if (s.length() == 0)
            return ans;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String tmp = s.substring(i, i+10);
            if (map.containsKey(tmp))
                map.put(tmp, map.get(tmp)+1);
            else map.put(tmp, 1);
        }

        for (String key : map.keySet()) {
            if (map.get(key) > 1)
                ans.add(key);
        }
        return ans;
    }

    /**
     * Rabin-Karp：使用旋转哈希实现常数时间窗口切片
     */
    public List<String> official_findRepeatedDnaSequences(String s) {
        int L = 10, n = s.length();
        if (n <= L) return new ArrayList();

        // rolling hash parameters: base a
        int a = 4, aL = (int)Math.pow(a, L);

        // convert string to array of integers
        Map<Character, Integer> toInt = new
                HashMap() {{put('A', 0); put('C', 1); put('G', 2); put('T', 3); }};
        int[] nums = new int[n];
        for(int i = 0; i < n; ++i) nums[i] = toInt.get(s.charAt(i));

        int h = 0;
        Set<Integer> seen = new HashSet();
        Set<String> output = new HashSet();
        // iterate over all sequences of length L
        for (int start = 0; start < n - L + 1; ++start) {
            // compute hash of the current sequence in O(1) time
            if (start != 0)
                h = h * a - nums[start - 1] * aL + nums[start + L - 1];
                // compute hash of the first sequence in O(L) time
            else
                for(int i = 0; i < L; ++i) h = h * a + nums[i];
            // update output and hashset of seen sequences
            if (seen.contains(h)) output.add(s.substring(start, start + L));
            seen.add(h);
        }
        return new ArrayList<String>(output);
    }

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        FindRepeatedDnaSequences187 findRepeatedDnaSequences187 = new FindRepeatedDnaSequences187();
        System.out.println(findRepeatedDnaSequences187.findRepeatedDnaSequences(s));
    }
}
