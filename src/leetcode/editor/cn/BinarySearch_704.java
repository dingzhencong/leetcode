package leetcode.editor.cn;
//给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否
//则返回 -1。 
//
// 
//示例 1: 
//
// 输入: nums = [-1,0,3,5,9,12], target = 9
//输出: 4
//解释: 9 出现在 nums 中并且下标为 4
// 
//
// 示例 2: 
//
// 输入: nums = [-1,0,3,5,9,12], target = 2
//输出: -1
//解释: 2 不存在 nums 中因此返回 -1
// 
//
// 
//
// 提示： 
//
// 
// 你可以假设 nums 中的所有元素是不重复的。 
// n 将在 [1, 10000]之间。 
// nums 的每个元素都将在 [-9999, 9999]之间。 
// 
// Related Topics 二分查找

//二分查找
class BinarySearch_704{
    public static void main(String[] args) {
        int[] arrys = new int[]{1, 2, 2, 3, 3, 4, 5, 7, 7};
        System.out.println(biSearchFirst(arrys, 2));
        System.out.println(biSearchLast(arrys, 2));
    }


    /**
     * 查找第一个元素出现的位置（元素允许重复）
     * @param array
     * @param a
     * @return
     */
    public static int biSearchFirst(int[] array, int a) {
        int n = array.length;
        int low = 0;
        int hi = n - 1;
        int mid = 0;
        while (low < hi) {
            mid = (low + hi) / 2;
            if (array[mid] < a) {
                low = mid + 1;
            } else {
                hi = mid;
            }
        }
        if (array[low] != a) {
            return -1;
        } else {
            return low;
        }
    }

    /**
     * 查询元素最后一次出现的位置
     * @param array
     * @param a
     * @return
     */
    public static int biSearchLast(int[] array, int a) {
        int n = array.length;
        int low = 0;
        int hi = n - 1;
        int mid = 0;
        while (low < hi) {
            mid = (low + hi + 1) / 2;
            if (array[mid] <= a) {
                low = mid;
            } else {
                hi = mid - 1;
            }
        }

        if (array[low] != a) {
            return -1;
        } else {
            return hi;
        }
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }


}
//leetcode submit region end(Prohibit modification and deletion)


}