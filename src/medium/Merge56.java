package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间
 */
public class Merge56 {
    public int[][] merge(int[][] intervals) {
        //看到返回值类型我好纠结了一下，果然还是应该用List
        List<int[]> ansList = new ArrayList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        /*
         * 下面这里是有问题的，因为没有考虑到输出可以多次合并的情况，比如[[1,4],[0,2],[3,5]]
         * 所以如果要这样做，还需要在for循环里面再加一个循环
         */
        for (int i=0; i < intervals.length; i++) {
            if (i+1 < intervals.length && intervals[i][1] >= intervals[i+1][0]) {
                //第二个数不一定谁大
                int secondNum = Math.max(intervals[i][1], intervals[i+1][1]);
                ansList.add(new int[]{intervals[i][0], secondNum});
                i++;
            }
            else {
                ansList.add(intervals[i]);
            }
        }
        return ansList.toArray(new int[ansList.size()][]);
    }

    public int[][] others_merge(int[][] arr) {
        if(arr == null || arr.length<=1)
            return arr;
        List<int[]> list = new ArrayList<>();
        //Arrays.sort(arr,(a,b)->a[0]-b[0]);
        Arrays.sort(arr,new Comparator<int[]>(){
            @Override
            public int compare(int[] a,int[] b){
                return a[0]-b[0];
            }
        });
        int i=0;
        int n = arr.length;
        while(i<n){
            int left = arr[i][0];
            int right = arr[i][1];
            while(i<n-1 && right>=arr[i+1][0]){
                right = Math.max(right,arr[i+1][1]);
                i++;
            }
            list.add(new int[] {left,right});
            i++;
        }
        return list.toArray(new int[list.size()][2]);
    }
}
