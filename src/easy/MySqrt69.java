package easy;

/**
 * 这个题虽然通过了，但是理想的做法应该是二分查找才对
 */
public class MySqrt69 {
    public int mySqrt(int x) {
        if (x==0 || x== 1)
            return x;
        long bigOne = x;
        long smallOne = x;
        while (true) {
            //下面这里如果smallOne是int类型，平方后可能会越界
            if (smallOne*smallOne > x) {
                bigOne = smallOne;
                smallOne /= 2;
            }
            else break;
        }
        for (long i = smallOne; i <= bigOne; i++) {
            if (i*i > x)
                return (int)i-1;
        }
        //到这里就失败了
        return 0;
    }

    public static void main(String[] args) {
        MySqrt69 mySqrt69 = new MySqrt69();
        System.out.println(mySqrt69.mySqrt(2147395599));
    }
}
