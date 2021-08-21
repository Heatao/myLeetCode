package interviewer;

import java.util.*;

public class Main8_0808 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        List<Character> inputList = new ArrayList<>();
        in.nextLine();
        String line = in.nextLine();

        if (N == 0 || N != line.length()) return;

        int index = 0;
        int lastScore = 0;
        List<Integer> outList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            char action = line.charAt(i);
            if (action == 'L') {
                if (index-1 >= 0) index--;
            }
            else if (action == 'R') {
                if (index+1 < inputList.size()) index++;
            }
            else if (action == 'D') {
                inputList.remove(index - 1);
                index--;
                lastScore = getScore(inputList);
            }
            else {
                inputList.add(index, action);
                lastScore = getScore(inputList);
                index++;
            }
            outList.add(lastScore);
        }
        for (int i = 0; i < N; i++) {
            System.out.print(outList.get(i));
            if (i+1 != N) System.out.print(" ");
        }
    }

    private static int getScore(List<Character> inputList) {
        int max = 0, score = 0, need = 0;
        Deque<Character> stack = new ArrayDeque<>();
        for (Character each : inputList) {
            if (stack.isEmpty()) score = 0;
            if (each == '(') stack.push('(');
            else {
                if (!stack.isEmpty()) {
                    stack.pop();
                    score += 1;
                    max = Math.max(max, score);
                }
                else {
                    need -= 1;
                }
            }
        }

        while (!stack.isEmpty()) {
            need--;
            stack.pop();
        }
        if (need == 0) return max;
        else return need;
    }
}
