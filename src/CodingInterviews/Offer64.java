package CodingInterviews;

public class Offer64 {
    // 可以用+和递归
    // 笑死，根本想不出
    // 下面是krahets的做法
    int res = 0;
    public int sumNums(int n) {
        boolean x = n > 1 && sumNums(n - 1) > 0;
        res += n;
        return res;
    }
}
