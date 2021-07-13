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
        System.out.println(mySqrt69.do2nd2(6));
    }

    private int do2nd(int x) {
        // 最朴素的做法，从1开始到x，试验其平方。简单优化：每次从x，x/2，x/2/2这样来判断
        long squareR = x;
        while(squareR*squareR > x) {
            squareR /= 2;
        }
        for(long i = squareR; i <= x; i++) {                        // 易错点：忽略了int的平方会溢出
            if(i*i <= x && (i+1)*(i+1) > x)
                return (int)i;
        }
        return 0;
    }

    // 看了提示应该用二分查找
    private int do2nd2(int x) {
        if(x == 1) return 1;
        long left = 0, right = x;
        while(left < right) {
            long mid = left + (right - left) / 2;
            if(mid*mid <= x && (mid+1)*(mid+1) > x)
                return (int)mid;
            else if(mid*mid < x)
                left = mid+1;
            else if(mid*mid > x)
                right = mid-1;
        }
        return (int)left;
    }
}
