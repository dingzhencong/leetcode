package leetcode.editor.cn;
//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 
// 👍 2890 👎 0

import java.util.*;

//三数之和
class ThreeSum_15{
    public static void main(String[] args) {
        Integer[] nums = new Integer[]{3, 2, 1, 4};
        Arrays.sort(nums, Collections.reverseOrder());
        System.out.println(Arrays.toString(nums));

    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i <nums.length ; i++) {
            List<List<Integer>> list = twoSum(nums, i+1, 0 - nums[i]);
            ListIterator iterator = list.listIterator();
            while (iterator.hasNext()) {
                List<Integer> l = (List<Integer>) iterator.next();
                l.add(nums[i]);
            }
            lists.addAll(list);
        }
        return lists;
    }

    public List<List<Integer>> twoSum(int[] numbers, int start,int target) {
        List<List<Integer>> list1 = new ArrayList<>();
        int l = start;
        int r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            int lnum = numbers[l];
            int rnum = numbers[r];
            while (sum > target && numbers[r] == rnum) {
                r--;
            }
            while (sum < target && numbers[l] == lnum) {
                l++;
            }
            if (sum == target) {
                List<Integer> list = new ArrayList<>();
                list.add(numbers[l]);
                list.add(numbers[r]);
                list1.add(list);
                while (l < r && numbers[l] == lnum) {
                    l++;
                }
                while (l < r && numbers[r] == rnum) {
                    r--;
                }
            }
        }
        return list1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}