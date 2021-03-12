package hard;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 下面这个解法超时了，这种问题要么是回溯，要么是数学
 */
public class GetPermutation60 {

    int combinations = 0;
    String ans;

    public String getPermutation(int n, int k) {
        int[] visited = new int[n];

        //用Java8 stream生成自然数
        List<Integer> range = IntStream.rangeClosed(1, n)
                .boxed().collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        backTrack(range, k, visited, sb);
        return ans;
    }

    /**
     *
     * @param nums 自然数列表
     * @param k 第几个
     * @param visited 访问数组
     * @return
     */
    public void backTrack(List<Integer> nums, int k, int[] visited, StringBuilder tmp) {
        if (tmp.length() == nums.size()) {
            combinations ++;
            if (combinations == k) {
                ans = tmp.toString();
            }
            return;
        }

        for (int i=0; i < nums.size(); i++) {
            if (visited[i] == 1)
                continue;
            visited[i] = 1;
            tmp.append(nums.get(i));
            backTrack(nums, k, visited, tmp);
            tmp.deleteCharAt(tmp.length()-1);
            visited[i] = 0;
        }
    }
}
