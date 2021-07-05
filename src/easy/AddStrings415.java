package easy;

/**
 * 不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 * 非负整数 num1 和 num2，无前导0，长度小于5100
 *
 * 思路很简单，流程也全部对了，但是对于Java API的使用有问题，而且写的很臃肿
 */
public class AddStrings415 {
    public String addStrings(String num1, String num2) {
        int len1 = num1.length();                                   // String是length()，数组和集合才是length
        int len2 = num2.length();
        // 让num2为更长者
        if(len1 > len2) {
            String tmp = num1;
            num1 = num2;
            num2 = tmp;
            int tmpn = len1;
            len1 = len2;
            len2 =tmpn;
        }

        // 注意有可能最后也存在进一位的情况哟
        // 直接reverse来做应该会简单点吧
        String num1r = new StringBuilder(num1).reverse().toString();
        String num2r = new StringBuilder(num2).reverse().toString();
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i;
        for(i = 0; i < len1; i++) {
            int n1 = num1r.charAt(i) - '0';
            int n2 = num2r.charAt(i) - '0';
            int sum = carry + n1 + n2;
            if(sum >= 10) {
                carry = 1;
                sum %= 10;
            }
            else carry = 0;
            sb.append(String.valueOf(sum));                // StringBuilder是append()，而且这里不能传入sum + '0'，因为StringBuilder默认是String呀
        }

        while(i < len2) {
            if(carry == 1) {
                int sum = num2r.charAt(i) - '0' + carry;
                if(sum >= 10) {
                    carry = 1;
                    sum %= 10;
                }
                else carry = 0;
                sb.append(sum);
            }
            else sb.append(num2r.charAt(i));
            i++;                                                    // 易忽略
        }
        if(carry == 1) sb.append('1');
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String s1 = "1";
        String s2 = "999";
        AddStrings415 addStrings415 = new AddStrings415();
        System.out.println(addStrings415.addStrings(s1, s2));
    }
}
