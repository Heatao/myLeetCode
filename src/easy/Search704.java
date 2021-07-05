package easy;

public class Search704 {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        return bisectSearch(nums, 0, nums.length, target);
    }

    private int bisectSearch(int[] nums, int first, int last, int target){
        if(first > last) return -1;
        while(first < last) {
            int mid = first + (last - first) / 2;
            if(nums[mid] < target)
                first = mid+1;
            else last = mid;
        }
        if(first < nums.length && nums[first] == target) return first;              // 易错点：忽略first是否超过范围
        else return-1;
    }
}
