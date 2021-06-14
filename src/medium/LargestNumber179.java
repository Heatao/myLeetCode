package medium;

public class LargestNumber179 {
    // 最朴素的思路是找到每个数中最大的那个数字
    // 新建一个数组和nums一样，该数组的每一个数字用第一位表示原位置的最大数字，第二位表示原位置最大数字的位置，第三位表示原位置的数字的长度
    // 后来我觉得这种思路无法确定大小呢，因为第三位和第三位都会影响大小，而且一个数字中可能出现多个9

    // 看了评论发现只需要比较s1 + s2 和 s2 + s1就行了呢，用一个冒泡就完事儿
    // 写完了之后觉得如果一开始把nums数组换成String数组代码会简洁很多
    // 看了评论区发现还可以利用sort接口，实现Comparator即可
    public String largestNumber(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            String numi = "" + nums[i];
            for (int j = i+1; j < n; j++) {
                String numj = "" + nums[j];
                // 两个int拼接可能超过int范围
                if (Long.parseLong(numi + numj) < Long.parseLong(numj + numi)) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                    numi = numj;                    // 这一句别忘了喔
                }//if
            }//inner for
        }//outer for

        // 还可能为0
        if (nums[0] == 0)
            return "0";

        StringBuilder ans= new StringBuilder();
        for (int num : nums) {
            ans.append(num);
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        LargestNumber179 largestNumber179 = new LargestNumber179();
        int[] nums = {10,2,9,39,17};
        System.out.println(largestNumber179.largestNumber(nums));
    }
}
