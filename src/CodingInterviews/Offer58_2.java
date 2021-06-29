package CodingInterviews;

public class Offer58_2 {
    /*
    之前做过这道题，可以反转两次来解
    krahets给出了很多题解https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/solution/mian-shi-ti-58-ii-zuo-xuan-zhuan-zi-fu-chuan-qie-p/
     */
    public String reverseLeftWords(String s, int n) {
        int len = s.length();
        if (len < n) throw new RuntimeException("Incorrect input data.");
        StringBuilder sb = new StringBuilder(s);
        String str1 = sb.substring(n);
        sb.reverse();
        String str2 = new StringBuilder(sb.substring(len - n)).reverse().toString();
        return str1 + str2;
    }

    public static void main(String[] args) {
        Offer58_2 offer58_2 = new Offer58_2();
        String s = "lrloseumgh";
        int n = 6;
        System.out.println(offer58_2.reverseLeftWords(s, n));
    }
}
