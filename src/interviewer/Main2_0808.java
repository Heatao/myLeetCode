package interviewer;

import java.util.Scanner;

public class Main2_0808 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        if (line.length() == 0) return;
        StringBuilder sb = new StringBuilder();
        line = line.replace(" ", "");
        sb.append(line.charAt(0));
        for (int i = 1; i < line.length(); i++) {
            if (line.charAt(i) == line.charAt(i-1)) {
                continue;
            }
            sb.append(line.charAt(i));
        }
        System.out.println(sb.toString());
    }
    //a iC C  C GmyyyySp p
}
