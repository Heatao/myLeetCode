package CodingInterviews;

/**
 * 在leetCode中是hard的题目哟
 * 两遍都没做出来，真的需要反思一下
 */
public class Offer11 {
    /**
     * 用左边位置 left 和中间位置 mid 的值进行比较是否可以？
     * 举例：[3, 4, 5, 1, 2] 与 [1, 2, 3, 4, 5] ，此时，中间位置的值都比左边大，但最小值一个在后面，一个在前面，因此这种做法不能有效地减治。
     *
     * 用右边位置 right 和中间位置 mid 的值进行比较是否可以？
     * 举例：[1, 2, 3, 4, 5]、[3, 4, 5, 1, 2]、[2, 3, 4, 5 ,1]，用右边位置和中间位置的元素比较，可以进一步缩小搜索的范围。
     *
     */
    public int minArray(int[] numbers) {
        int first = 0;
        int last = numbers.length - 1;
        while (first < last) {
            int mid = first + (last - first) / 2;
            if (numbers[mid] < numbers[last])
                last = mid;
            // 为什么按照下面这样写是错误的呢？我的理解，因为会出现重复元素，所以else的时候需要把重复的--
            // 因为else写的是last--，所以这里也是和last比
            // else if (numbers[mid] > numbers[first])
            // 这种多情况的，最好和一边比，这样可以考虑所有情况
             else if (numbers[mid] > numbers[last])
                first = mid + 1;
            else last--;
        }
        return numbers[first];
    }

    public static void main(String[] args) {
        int[] numbers = {2,2,2,0,1};
        Offer11 offer11 = new Offer11();
        System.out.println(offer11.minArray(numbers));
    }
}
