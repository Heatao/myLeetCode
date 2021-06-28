package CodingInterviews;

public class Offer58_1 {
    public String reverseWords(String s) {
        s = s.strip();
        String[] list = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = list.length-1; i >= 0; i--) {
            sb.append(list[i]);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
