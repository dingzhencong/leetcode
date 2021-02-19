package leetcode.editor.cn;
//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。 
//
// 
//
// 进阶： 
//
// 
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 
// 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？ 
// 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,2,3,4,5,6,7], k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 
//输入：nums = [-1,-100,3,99], k = 2
//输出：[3,99,-1,-100]
//解释: 
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 104 
// -231 <= nums[i] <= 231 - 1 
// 0 <= k <= 105 
// 
//
// 
// 
// Related Topics 数组 
// 👍 878 👎 0

//旋转数组
class RotateArray_189{
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        k %= length;
        reverse(nums, 0, length - 1);//先反转全部的元素
        reverse(nums, 0, k - 1);//在反转前k个元素
        reverse(nums, k, length - 1);//接着反转剩余的
    }

    //把数组中从[start，end]之间的元素两两交换,也就是反转
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}