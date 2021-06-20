package CodingInterviews;

public class Offer17 {
    public int[] printNumbers(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(9);
        }
        int max = Integer.parseInt(sb.toString());
        int[] ans = new int[max];
        for (int i = 0; i < max; i++) {
            ans[i] = i+1;
        }
        return ans;
    }
}
