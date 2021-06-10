package easy;

public class TitleToNumber171 {
    public int titleToNumber(String columnTitle) {
        int len = columnTitle.length();
        int ans = 0;
        for (int i = len-1; i >= 0; i--) {
            int j = len - 1 - i;
            // 因为它没有0，不是标准26进制，所以这里需要+1哟
            int tmp = columnTitle.charAt(j) - 'A' + 1;
            for (int k = 0; k < i; k++) {
                tmp  = tmp*26;
            }
            ans += tmp;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "ZZ";
        TitleToNumber171 titleToNumber171 = new TitleToNumber171();
        System.out.println(titleToNumber171.titleToNumber(s));
    }
}
