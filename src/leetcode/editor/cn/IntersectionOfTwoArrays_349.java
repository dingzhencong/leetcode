package leetcode.editor.cn;
//给定两个数组，编写一个函数来计算它们的交集。 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2]
// 
//
// 示例 2： 
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[9,4] 
//
// 
//
// 说明： 
//
// 
// 输出结果中的每个元素一定是唯一的。 
// 我们可以不考虑输出结果的顺序。 
// 
// Related Topics 排序 哈希表 双指针 二分查找 
// 👍 324 👎 0

import java.util.HashSet;

//两个数组的交集
class IntersectionOfTwoArrays_349{
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet();
        for (int num : nums1) {
            set1.add(num);
        }
        HashSet<Integer> set2 = new HashSet();
        for (int num : nums1) {
            set2.add(num);
        }
        HashSet<Integer> set3 = new HashSet();
        for (int num : set1) {
            if (set2.contains(num)) {
                set3.add(num);
            }
        }
        int[] nums = new int[set3.size()];
        int i = 0;
        for (int num : set3) {
            nums[i++] = num;
        }
        return nums;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}