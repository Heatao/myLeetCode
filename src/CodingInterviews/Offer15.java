package CodingInterviews;

public class Offer15 {
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32 && n != 0; i++) {
            count += n & 1;
            n = n >> 1;
        }
        return count;
    }
}
