package leetcode.editor.cn;
//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法

import java.util.LinkedList;
import java.util.List;

//全排列
class Permutations_46{
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> solution = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        track(nums, track);
        return solution;
    }

    public void track(int[] nums, LinkedList<Integer> track) {
        if (nums.length == track.size()) {
            solution.add(new LinkedList(track));
        }
        for (int num : nums) {
            if (track.contains(num)) {
                continue;
            }
            track.add(num);
            track(nums, track);
            track.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}