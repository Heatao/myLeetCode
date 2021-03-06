package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode18.四数之和
 */
public class fourSum {
    /**
     * 双指针，注意自己写的注释
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> mySolution_fourSum(int[] nums, int target) {
        List<List<Integer>> ansList = new ArrayList<>();
        int numsLen=nums.length;
        if (numsLen < 4) return ansList;
        Arrays.sort(nums);

        for (int first=0; first < numsLen; first++){
            if (first>0 && nums[first]==nums[first-1]) continue;
            int target234 = target - nums[first];

            //这几个for循环的结束条件都是<numsLen，因为second=first+1，所以判断没错，不会溢出
            for (int second=first+1; second < numsLen; second++){
                if (second>first+1 && nums[second]==nums[second-1]) continue;
                int target34 = target234 - nums[second];

                //双指针快的原因就在于最后一个指针不是每次都重头开始的
                int fourth = numsLen-1;
                for (int third=second+1; third < numsLen; third++){
                    if (third>second+1 && nums[third]==nums[third-1]) continue;

                    //这句放在这里应该不会错，但是并没有快
//                    int fourth = numsLen-1;

                    while (fourth > third && nums[third] + nums[fourth] > target34) fourth--;
                    //此时要么3和4的指针重合，要么是已经满足要求了
                    if (fourth == third) break;

                    //这里的判断是因为可能target34大于3和4之和，这就意味着third需要右移动
                    if (nums[third] + nums[fourth] == target34){
                        List<Integer> thisAns = new ArrayList<>();
                        thisAns.add(nums[first]);
                        thisAns.add(nums[second]);
                        thisAns.add(nums[third]);
                        thisAns.add(nums[fourth]);
                        ansList.add(thisAns);
                    }
                }
            }
        }
        return ansList;
    }

    /**
     * 作者：you-wei-wu
     * 链接：https://leetcode-cn.com/problems/4sum/solution/ji-bai-9994de-yong-hu-you-dai-ma-you-zhu-shi-by-yo/
     * 他人的做法，进行了一些剪枝的优化
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> others_fourSum(int[] nums,int target){
        /*定义一个返回值*/
        List<List<Integer>> result=new ArrayList<>();
        /*当数组为null或元素小于4个时，直接返回*/
        if(nums==null||nums.length<4){
            return result;
        }
        /*对数组进行从小到大排序*/
        Arrays.sort(nums);
        /*数组长度*/
        int length=nums.length;
        /*定义4个指针k，i，j，h  k从0开始遍历，i从k+1开始遍历，留下j和h，j指向i+1，h指向数组最大值*/
        for(int k=0;k<length-3;k++){
            /*当k的值与前面的值相等时忽略*/
            if(k>0&&nums[k]==nums[k-1]){
                continue;
            }
            /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏*/
            int min1=nums[k]+nums[k+1]+nums[k+2]+nums[k+3];
            if(min1>target){
                break;
            }
            /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
            int max1=nums[k]+nums[length-1]+nums[length-2]+nums[length-3];
            if(max1<target){
                continue;
            }
            /*第二层循环i，初始值指向k+1*/
            for(int i=k+1;i<length-2;i++){
                /*当i的值与前面的值相等时忽略*/
                if(i>k+1&&nums[i]==nums[i-1]){
                    continue;
                }
                /*定义指针j指向i+1*/
                int j=i+1;
                /*定义指针h指向数组末尾*/
                int h=length-1;
                /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏*/
                int min=nums[k]+nums[i]+nums[j]+nums[j+1];
                if(min>target){
                    break;
                }
                /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
                int max=nums[k]+nums[i]+nums[h]+nums[h-1];
                if(max<target){
                    continue;
                }
                /*开始j指针和h指针的表演，计算当前和，如果等于目标值，j++并去重，h--并去重，当当前和大于目标值时h--，当当前和小于目标值时j++*/
                while (j<h){
                    int curr=nums[k]+nums[i]+nums[j]+nums[h];
                    if(curr==target){
                        result.add(Arrays.asList(nums[k],nums[i],nums[j],nums[h]));
                        j++;
                        while(j<h&&nums[j]==nums[j-1]){
                            j++;
                        }
                        h--;
                        while(j<h&&i<h&&nums[h]==nums[h+1]){
                            h--;
                        }
                    }else if(curr>target){
                        h--;
                    }else {
                        j++;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,0,-1,0,-2,2};
        int target = 0;
        System.out.println(mySolution_fourSum(nums, target));
    }
}
