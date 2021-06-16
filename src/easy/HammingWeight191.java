package easy;

/**
 * 做了190，这道题稍微有点思路了
 * 并且还可以Integer.bitCount(n);
 */
public class HammingWeight191 {
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32 && n != 0; i++) {
            int n1 = n & 1;
            n = n >> 1;
            if (1 == n1)
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        HammingWeight191 hammingWeight191 = new HammingWeight191();
        int n1 = 0b00000000000000000000000000001011;
        int n2 = 0b11111111111111111111111111111101;
        System.out.println(hammingWeight191.hammingWeight(n2));
    }
}
