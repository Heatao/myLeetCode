package interviewer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main8_2_0808 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int lens = in.nextInt();
        in.nextLine();
        char[] tmp = in.nextLine().toCharArray();
        List<Character> list = new LinkedList<>();
        int mos = 0;
        int val = 0;
        for(int i=0;i<lens;i++){
            if (tmp[i]=='R'){
                if (mos<list.size()){
                    mos++;
                }
                System.out.print(val);
                System.out.print(" ");
            }else if (tmp[i]=='L'){
                if(mos>0){
                    mos--;
                }
                System.out.print(val);
                System.out.print(" ");
            }else if (tmp[i]=='D'){
                if (mos>=1){
                    list.remove(mos-1);
                    mos--;
                    val = validate(list);
                }
                System.out.print(val);
                System.out.print(" ");
            }else{
                list.add(mos,tmp[i]);
                mos++;
                val = validate(list);
                System.out.print(val);
                System.out.print(" ");
            }
        }
    }
    public static int validate(List<Character> list){
        int lens = list.size();
        int max = 0;
        int min = 0;
        Deque<Character> tmp = new LinkedList<>();
        for (int i=0;i<lens;i++){
            char c = list.get(i);
            if (tmp.isEmpty()&&c==')'){
                min--;
            }else if (c=='('){
                tmp.push(c);
            }else if(c==')'&&tmp.peek()=='('){
                max = Math.max(tmp.size(),max);
                tmp.pop();
            }
        }
        if (tmp.isEmpty()){
            if (min==0){
                return max;
            }else{
                return min;
            }
        }else {
            return min-tmp.size();
        }

    }
}