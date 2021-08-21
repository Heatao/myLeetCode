package interviewer;

import java.util.*;

public class GetPostOrder {
    /**
     * E D C A B F
     * D A C B E F
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        String[] preOrder = in.nextLine().split(" ");
//        String[] inOrder = in.nextLine().split(" ");
        String line1 = "E D C A B F";
        String line2 = "D A C B E F";
        String[] preOrder = line1.split(" ");
        String[] inOrder = line2.split(" ");
        GetPostOrder main = new GetPostOrder();
        int i = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for(String each : inOrder) {
            map.put(each, i);
            i++;
        }
        int len = preOrder.length;
        main.getLastOrder(preOrder, inOrder, 0, len-1, 0, len-1, map);
        Collections.reverse(main.postOrder);
        System.out.println(main.postOrder.toString());
    }

    public ArrayList<String> postOrder = new ArrayList<>();
    private void getLastOrder(String[] preOrder, String[] inOrder,
                              int preLeft, int preRight, int inLeft, int inRight, HashMap<String, Integer> map){
        if(preOrder == null || inOrder == null) return;
        if(preLeft > preRight || inLeft > inRight || preLeft < 0 || inLeft < 0
                || preRight >= preOrder.length || inRight >= inOrder.length) return;

        String pivot = preOrder[preLeft];
        postOrder.add(pivot);
        int pIndex = map.get(pivot);
        getLastOrder(preOrder, inOrder, preRight+pIndex-inRight+1, preRight, pIndex+1, inRight, map);
        getLastOrder(preOrder, inOrder, preLeft+1, pIndex-inLeft+preLeft, inLeft, pIndex-1, map);
    }
}
