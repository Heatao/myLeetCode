package medium;

import java.util.Arrays;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Multiply43 {
    /**
     * 思路：模仿竖式乘法，将num1也进一步拆分，变成更笨的乘法，放到list最后加起来（加起来不就变成数字了吗，貌似要求不可转string诶）
     * 参考题解的方法，可以维护一个数组，没两个数字计算落下的位置是固定可以知道的。
     * 有很多细节需要考虑，比如二次进位的时候如何处理
     *
     * 最后一个测试用例失败，我真傻，真的，既然题目要求不是直接转，那么我用int数组存早完事儿了，或者一开始的思路用int的List，不要曲解题目啊啊啊啊！！！！
     */
    public String mySolution_multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        //最多就这么长，初始化char数组
        char[] resultList = new char[num1.length()+num2.length()];
        Arrays.fill(resultList, 'a');

        //表示从右向左移动几位
        int tenTagFirst = 1;
        int tenTagSecond = 1;
        int listLen = resultList.length;

        for (int i = num1.length()-1; i >= 0; i--) {
            for (int j = num2.length()-1; j >= 0; j--) {
                int tmp = (num1.charAt(i)-'0')*(num2.charAt(j)-'0');
                int rightNum = tmp % 10;
                int leftNumn = tmp / 10;

                //Java初始化数组应该都为0，下面这个index可能有错
                int thisIndex = listLen - tenTagFirst;
                if (resultList[thisIndex] != 'a') {
                    //这里需要考虑二次进位的情况，大于57就重新搞，只有右边的数需要考虑此类情况的溢出
                    if (resultList[thisIndex] + rightNum > 57){
                        resultList[thisIndex] = (char)(resultList[thisIndex] + rightNum - 10);
                        if (resultList[thisIndex-1] != 'a') {
                            resultList[thisIndex-1] = (char)(resultList[thisIndex-1] + 1);
                        }
                        //这里再进位只可能是1
                        else resultList[thisIndex-1] = (char)(1 + '0');
                    }
                    else
                        resultList[thisIndex] = (char)(resultList[thisIndex] + rightNum);
                }
                else {
                    resultList[thisIndex] = (char)(rightNum + '0');
                }

                if (leftNumn != 0) {
                    if (resultList[thisIndex-1] != 'a') {
                        //同样的道理，初始化之后可能会溢出
                        if (resultList[thisIndex-1] + leftNumn > 57){
                            resultList[thisIndex-1] = (char)(resultList[thisIndex-1] + leftNumn - 10);
                            if (resultList[thisIndex-2] != 'a') {
                                resultList[thisIndex-2] = (char)(resultList[thisIndex-2] + 1);
                            }
                            //这里再进位只可能是1
                            else resultList[thisIndex-2] = (char)(1 + '0');
                        }
                        else
                            resultList[thisIndex-1] = (char)(resultList[thisIndex-1] + leftNumn);
                    }
                    else {
                        resultList[thisIndex-1] = (char)(leftNumn + '0');
                    }
                }
                //移动的位数向前一位
                tenTagFirst += 1;
            }
            //拆分竖式乘法
            tenTagFirst = tenTagSecond+1;
            tenTagSecond += 1;
        }
        //构造String
        StringBuilder resultString = new StringBuilder();
        int startTag = 0;
        for (char c : resultList) {
            if (c == 'a' && startTag == 0) {
                continue;
            }
            startTag = 1;
            resultString.append(c);
        }
        return resultString.toString();
    }

    public static void main(String[] args) {
        String num1 = "999";
        String num2 = "999";
        Multiply43 multiply43 = new Multiply43();
        System.out.println(multiply43.mySolution_multiply(num1, num2));
    }
}
