package hard;

/**
 * LeetCode4.寻找两个正序数组的中位数
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class findMedianSortedArrays {
    public double mySolution_findMedianSortedArrays(int[] nums1, int[] nums2) {
        /**
         *
         * 复习知识点：
         * 1. 数组被创建之后，长度就是固定的，而且会初始化为0，判断是否为空就是长度是否为0
         * 2. 想起卜老师说的，先考虑最简单的做法
         * 最简单的情况，num1只有1个，num2为空，返回num1的；num1和num2各有一个数，返回二者中间的；
         * num1有2个，num2有1个，此时要达到log(m+n)，不能用排序，不能把3都值都访问到，比如示例1，此时num1的中位数是2，num2的值2>=num1中位数2，
         * 证明num2在num1中间，直接返回；
         * num1有2个，num2有2个时，能否把这个情况变到之前的情况呢？
         * [1,5],[2,100] 1 2 5 100
         * [1,5]，[2,5,6,10,20,50,100]
         * 2 4 5 15
         * 1 7 8 10 17
         * 1 2 4 5 7 8 10 15 17
         */
        int num1_length = nums1.length;
        int num2_length = nums2.length;

        return 0;
    }

    public double others_findMedianSortedArrays(int[] nums1, int[] nums2) {
        /**
         * 条件时间复杂度为O(log (m+n))，则暗示用Divide and Conquer
         * 消除奇偶的trick：我们分别找第 (m+n+1) / 2 个，和 (m+n+2) / 2 个，然后求其平均值即可，这对奇偶数均适用。加入 m+n 为奇数的话，那么其实 (m+n+1) / 2 和 (m+n+2) / 2 的值相等，相当于两个相同的数字相加再除以2，还是其本身。
         * 这个题相当于在两个有序数组中找到第k小的元素
         *
         * 来源https://leetcode-cn.com/problems/median-of-two-sorted-arrays/comments/36497
         *
         * 思路：
         * 取两个数组中的第k/2个元素进行比较，如果数组1的元素小于数组2的元素，则说明数组1中的前k/2个元素不可能成为第k个元素的候选，
         * 所以将数组1中的前k/2个元素去掉，组成新数组和数组2求第k-k/2小的元素，因为我们把前k/2个元素去掉了，所以相应的k值也应该减小。
         * 另外就是注意处理一些边界条件问题，比如某一个数组可能为空或者k为1的情况。
         */
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }
    //i: nums1的起始位置 j: nums2的起始位置
    public int findKth(int[] nums1, int i, int[] nums2, int j, int k){
        if( i >= nums1.length) return nums2[j + k - 1];//nums1为空数组
        if( j >= nums2.length) return nums1[i + k - 1];//nums2为空数组
        if(k == 1){
            return Math.min(nums1[i], nums2[j]);
        }
        /**
         * 首先，为了避免产生新的数组从而增加时间复杂度，我们使用两个变量i和j分别来标记数组nums1和nums2的起始位置。
         * 然后来处理一些边界问题，比如当某一个数组的起始位置大于等于其数组长度时，
         * 说明其所有数字均已经被淘汰了，相当于一个空数组了，那么实际上就变成了在另一个数组中找数字，直接就可以找出来了。
         * 还有就是如果K=1的话，那么我们只要比较nums1和nums2的起始位置i和j上的数字就可以了。
         *
         * 难点就在于一般的情况怎么处理？
         * 因为我们需要在两个有序数组中找到第K个元素，为了加快搜索的速度，我们要使用二分法，对K二分，意思是我们需要分别在nums1和nums2中查找第K/2个元素，
         * 注意这里由于两个数组的长度不定，所以有可能某个数组没有第K/2个数字，
         * 所以我们需要先检查一下，数组中到底存不存在第K/2个数字，如果存在就取出来，否则就赋值上一个整型最大值，因为此时无法淘汰这个数组。
         * 如果某个数组没有第K/2个数字，那么我们就淘汰另一个数字的前K/2个数字即可。
         * 有没有可能两个数组都不存在第K/2个数字呢，这道题里是不可能的，因为我们的K不是任意给的，而是给的m+n的中间值，所以必定至少会有一个数组是存在第K/2个数字的。
         *
         * 最后就是二分法的核心啦，比较这两个数组的第K/2小的数字midVal1和midVal2的大小，
         * 如果第一个数组的第K/2个数字小的话，那么说明我们要找的数字肯定不在nums1中的前K/2个数字，
         * 所以我们可以将其淘汰，将nums1的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归。
         * 反之，我们淘汰nums2中的前K/2个数字，并将nums2的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归即可。
         */
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if(midVal1 < midVal2){
            return findKth(nums1, i + k / 2, nums2, j , k - k / 2);
        }else{
            return findKth(nums1, i, nums2, j + k / 2 , k - k / 2);
        }
    }
}
