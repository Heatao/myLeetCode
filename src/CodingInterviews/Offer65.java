package CodingInterviews;

public class Offer65 {
    /**
     * n=a⊕b            非进位和：异或运算
     * c=a&b<<1         进位：与运算+左移一位
     * 来自krehats，递归的方法更好理解
     */
    public int add(int a, int b) {
        if (b == 0) {
            return a;
        }

        // 转换成非进位和 + 进位
        return add(a ^ b, (a & b) << 1);
    }
}
