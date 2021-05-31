package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 额外要求是空间复杂度为O(n),应该是可以的，复用一个List
 */
public class GetRow119 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> innerRow = new ArrayList<>(rowIndex);
        // 这里需要和面试官确定一下起始行是什么
        // if (rowIndex == 0)
        //     return innerRow;
        innerRow.add(1);

        int prevNum = 0, retainNum;
        for (int i = 0; i < rowIndex; i++) {
            int len = innerRow.size();
            for (int j = 0; j <= len; j++) {
                retainNum = prevNum;
                if (j != len)
                    prevNum = innerRow.get(j);
                else {
                    prevNum = 0;
                    innerRow.add(1);
                }

                if (j != 0 && j != len) {
                    innerRow.set(j, prevNum + retainNum);
                }
            }
        }
        return innerRow;
    }
}
