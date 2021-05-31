package easy;

import java.util.ArrayList;
import java.util.List;

public class Generate118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> rows = new ArrayList<>();
        if (numRows == 0)
            return rows;
        List<Integer> innerRow = new ArrayList<>();
        innerRow.add(1);
        rows.add(innerRow);

        //完全可以把第一行也纳入近来的！因为第一行j==1，所以不会超出界限
        for (int i = 1; i < numRows; i++) {
            innerRow = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i)
                    innerRow.add(1);
                else {
                    int thisNum = rows.get(i-1).get(j) + rows.get(i-1).get(j-1);
                    innerRow.add(thisNum);
                }
            }
            rows.add(innerRow);
        }
        return rows;
    }
}
