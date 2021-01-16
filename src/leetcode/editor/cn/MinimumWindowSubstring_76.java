package leetcode.editor.cn;
//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
//
// 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 105 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 902 👎 0

import java.util.HashMap;

//最小覆盖子串
class MinimumWindowSubstring_76{
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> need = new HashMap<>();
        for (Character c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int valid = 0;

        // 记录最小覆盖子串的起始索引及长度
        int start = 0;
        int length = Integer.MAX_VALUE;
        /**
         * 1、当移动 right 扩大窗口，即加入字符时，应该更新哪些数据？ window、valid
         * 2、什么条件下，窗口应该暂停扩大，开始移动 left 缩小窗口？ valid==need.size
         * 3、当移动 left 缩小窗口，即移出字符时，应该更新哪些数据？ window、valid
         * 4、我们要的结果应该在扩大窗口时还是缩小窗口时进行更新？left++
         */
        while (right < s.length()) {
            // c 是将移入窗口的字符
            Character c = s.charAt(right);
            //右移窗口
            right++;
            //更新数据
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            //判断左窗口是否收缩
            if (valid == need.size()) {
                //判断是否最小字串
                if (right - left < length) {
                    start = left;
                    length = right - left;
                }

                Character d = s.charAt(left);
                left++;

                //更新窗口数据
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
            }

        }
        return length == Integer.MAX_VALUE ? "" : s.substring(start, start + length);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}